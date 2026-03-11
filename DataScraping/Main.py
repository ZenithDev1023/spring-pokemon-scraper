import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import re

from bs4 import BeautifulSoup
import requests
import warnings
import urllib3
import time

# Selenium imports
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC



# Pokémon Product Analytics: Comparative Web Scraping & Market Segmentation  

# Web Scraping Functions
def scrape_with_selenium(base_url):
    options = Options()
    options.add_argument('--headless')
    options.add_argument('--no-sandbox')
    options.add_argument('--disable-dev-shm-usage')

    driver = webdriver.Chrome(options=options)

    try:
        driver.get(base_url)
        time.sleep(2) # Allow page to load
        return driver.page_source
    finally:
        driver.quit()


def scrape_pages_with_selenium(base_url, max_products=755):
    # Advanced multi-page scraping using Selenium with hybird requests fallback.
    
    print("\n", "-"*50)
    print("Selenium Multi-page Scraping")
    print("-"*50)

    options = Options()
    options.add_argument('--headless')
    options.add_argument('--no-sandbox')
    options.add_argument('--disable-dev-shm-usage')
    options.add_argument('--disable-gpu')
    options.add_argument('--disable-extensions')
    options.add_argument('--disable-logging')
    options.add_argument('--log-level=3')

    driver = webdriver.Chrome(options=options)
    wait = WebDriverWait(driver, 30) # Wait for elements


    # Track Selenium operations
    selenium_metrics = {
        'total_pages_accessed': 0,
        'listing_pages_navigated': 0,
        'product_pages_accessed_selenium': 0,
        'product_pages_accessed_requests': 0,
        'selenium_failures': 0,
        'requests_fallbacks': 0,
        'next_button_clicks': 0,
        'back_navigations': 0
    }
    
    driver.get(base_url)
    selenium_metrics['total_pages_accessed'] += 1
    selenium_metrics['listing_pages_navigated'] += 1
    time.sleep(2) 

    
    # Dictionary for scraped products
    productdict = {
        'name': [],
        'price': [],
        'description': [],
        'stock': [], 
        'sku': [],
        'category': [],
        'tag': [],
        'weight': [],
        'dimension': [],
        'scraping_method': [] # Tracks if Selenium or requests is used
    }

    unique_products = set() # Saves items to ensure products are unique
    product_count = 0
    page_num = 1
    max_pages = 50

    print(f"Starting on page {page_num}: {driver.current_url}")

    # Main scraping loop until max_products reached
    while product_count < max_products and page_num <= max_pages:
        try:
            # Wait for product elements to load
            wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "li.product")))
            time.sleep(1)
        except:
            print(f"Warning: No products found on page {page_num} after waiting")
            break
            
        # Parse current page with BeautifulSoup
        soup = BeautifulSoup(driver.page_source, "html.parser")
        products = soup.find_all('li', class_='product')
        print(f"Page {page_num}: Found {len(products)} products")

        # Extracts product links, titles, and price from the listing page
        product_links = []
        product_titles = []
        product_prices = []
        
        for p in products:
            # Extracts the products URL
            a = p.find('a', class_='woocommerce-LoopProduct-link')
            if a and a.get('href'):
                product_links.append(a.get('href'))

                # Extracts the products title
                title_elem = p.find('h2', class_='woocommerce-loop-product__title')
                if title_elem:
                    title = title_elem.get_text().strip() if title_elem else "No title"
                else:
                    title = "No title"
                product_titles.append(title)

                # Extracts the products price
                price_elem = p.find('span', class_='price')
                price = "No price"
                if price_elem:
                    amount_elem = price_elem.find('span', class_='woocommerce-Price-amount amount')
                    if amount_elem:
                        price = amount_elem.get_text().strip()
                product_prices.append(price)

        # Processes each product on the current page
        for i, product_url in enumerate(product_links):
            if product_count >= max_products:
                break

            title = product_titles[i] if i < len(product_titles) else f"Product: {i}"

            listing_price = product_prices[i] if i < len(product_prices) else f"Product: {i}"

            # Skips duplicates
            if title in unique_products:
                print(f"Skipping duplicate {title}")
                continue

            print(f"\nProcessing [{product_count+1}/{max_products}]: {title}")
            print(f"URL: {product_url}")

            # Initialises product details with default
            product_details = {
                'name': title,
                'price': 0,
                'description': "No description",
                'stock': "Unknown",
                'sku': "NO SKU",
                'category': "No category",
                'tag': "No tag",
                'weight': "No weight",
                'dimension': "No dimension",
                'scraping_method': "Unknown"
            }

            product_soup = None
            max_selenium_retries = 3
            scraping_method = "Unknown"
            
            # Selenium first 
            for attempt in range(max_selenium_retries):
                try:
                    print(f"Attempt {attempt+1}: Using Selenium")
                    driver.get(product_url)
                    selenium_metrics['total_pages_accessed'] += 1
                    wait.until(EC.presence_of_element_located((By.TAG_NAME, "h1")))
                    time.sleep(1)
                    product_soup = BeautifulSoup(driver.page_source, "html.parser")
                    scraping_method = 'Selenium'
                    product_details['scraping_method'] = 'Selenium'
                    selenium_metrics['product_pages_accessed_selenium'] += 1
                    print("Selenium successful")

                    # Navigates back
                    if i < len(product_links) - 1:
                        driver.back()
                        wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "li.product")))
                        selenium_metrics['back_navigations'] += 1
                        time.sleep(0.5)
                    break
                
                except Exception as e:
                    print(f"Selenium attempt {attempt+1} failed:")
                    
                    if attempt == max_selenium_retries - 1:
                        selenium_metrics['selenium_failures'] += 1
                        print("All selenium attempts failed, trying the requests fallback.")

            if product_soup is None:
                try:
                    print("Trying requests fallback.")
                    response = requests.get(product_url, timeout=15, verify=False)
                    
                    if response.status_code == 200:
                        product_soup = BeautifulSoup(response.text, "html.parser")
                        scraping_method = "Requests (fallback)"
                        product_details['scraping_method'] = "Requests (fallback)"
                        selenium_metrics['product_pages_accessed_requests'] += 1
                        selenium_metrics['requests_fallbacks'] += 1
                        print("Requests fallback successful")
                    else:
                        print(f"Requests failed: {response.status_code}")
                        continue
                        
                except Exception as e:
                    print("Requests fallback failed:")
                    continue
            
            # Extract detailed product information from product page
            if product_soup:
                # Product title
                product_title_elem = product_soup.find('h1', class_='product_title')
                if product_title_elem:
                    title = product_title_elem.get_text().strip()
                
                # Price (prefer product page price over listing price)
                price_elem = product_soup.find('p', class_='price')
                price = listing_price # Default to listing price
                if price_elem:
                    amount_elem = price_elem.find('span', class_='woocommerce-Price-amount')
                    
                    if amount_elem:
                        price = amount_elem.get_text().strip()
                    else:
                        price = price_elem.get_text().strip()
                
                # Converts price to numeric type
                try:
                    price_numeric = float(price.replace('£', '').replace(',', '').strip()) if price != "No price" else 0
                except Exception as e:
                    price_numeric = 0 # Handles price conversion errors
                
                # Product description
                try:
                    desc_elem = product_soup.find('div', class_='woocommerce-product-details__short-description')
                    if desc_elem:
                        description = desc_elem.get_text().strip() if desc_elem else "No description"
                except:
                    pass
                
                # Product stock status
                try:
                    stock_elem = product_soup.find('p', class_='stock in-stock')
                    if stock_elem:
                        stock = stock_elem.get_text().strip() if stock_elem else "Out of stock"
                except:
                    pass
                
                # Product SKU (Stock Keeping Unit)
                try:
                    sku_elem = product_soup.find('span', class_='sku')
                    if sku_elem:
                        sku = sku_elem.get_text().strip() if sku_elem else "No sku"
                except:
                    pass
                
                # Product category
                try:
                    category_elem = product_soup.find('span', class_='posted_in')
                    if category_elem:
                        category_text = category_elem.get_text().strip()
                        category = category_text.replace('Categories:', '').strip()
                except:
                    pass
                
                # Product tags
                try:
                    tag_elem = product_soup.find('span', class_='tagged_as')
                    if tag_elem:
                        tag_text = tag_elem.get_text().strip()
                        tag = tag_text.replace('Tags:', '').strip()
                except:
                    pass
                
                # Product weight
                try:
                    weight_elem = product_soup.find('td', class_='product_weight')
                    if weight_elem:
                        weight = weight_elem.get_text().strip() if weight_elem else "No weight"
                except:
                    pass
                
                # Product dimensions     
                try:
                    dimension_elem = product_soup.find('td', class_='product_dimensions')
                    if dimension_elem:
                        dimension = dimension_elem.get_text().strip() if dimension_elem else "No dimension"
                except:
                    pass
                
                # Adds product to collection if its not a duplicate
                if title in unique_products:
                    continue
                    
                
                # Appends all extracted data to the dictionary
                productdict['name'].append(title)
                productdict['price'].append(price_numeric)
                productdict['description'].append(description)
                productdict['stock'].append(stock)
                productdict['sku'].append(sku)
                productdict['category'].append(category)
                productdict['tag'].append(tag)
                productdict['weight'].append(weight)
                productdict['dimension'].append(dimension)
                productdict['scraping_method'].append(scraping_method)
                
                unique_products.add(title)
                product_count += 1

                if product_count >= max_products:
                    print(f"\nReached maximum products ({max_products}). Stopping.")
                    break
                
                print(f" Scraped: {title} - £{price_numeric}")
                print(f" Stock: {stock}, SKU: {sku}")


        # Navigates to the next page if max_products hasn't been reached
        if product_count < max_products:
            try:
                # Finds and clicks the next page button
                next_page_num = page_num + 1
                next_page_url = (f"{base_url}page/{next_page_num}/")
                print(f"Navigating to {next_page_url}")
                
                driver.get(next_page_url)
                selenium_metrics['total_pages_accessed'] += 1
                selenium_metrics['listing_pages_navigated'] += 1
                time.sleep(3)

                page_num = next_page_num
                print(f"Successfully navigated to page {page_num}")
                print(f"Current URL: {driver.current_url}")
                    

                try:
                    wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "li.product")))
                    print(f"Products loaded successfully on page {page_num}")

                    soup = BeautifulSoup(driver.page_source, "html.parser")
                    products = soup.find_all('li', class_='product')
                    if len(products) >= 1:
                        print(f"Found {len(products)} products on page {page_num}")
                except:
                    print(f"Warning: No products detected on page {page_num + 1} after waiting")
            
            except Exception as e:
                print(f"\n No more pages found or navigation failed")
                break

    
    driver.quit()
    
    print("\n", "-"*50)
    print("--- Scraping Complete - Selenium Metrics ---")
    print("-"*50)
    print(f"Total pages accessed: {selenium_metrics['total_pages_accessed']}")
    print(f"Listing pages Navigated: {selenium_metrics['listing_pages_navigated']}")
    print(f"Product pages accessed via Selenium: {selenium_metrics['product_pages_accessed_selenium']}")
    print(f"Product pages accessed via Requests: (fallback) {selenium_metrics['product_pages_accessed_requests']}")
    print(f"Selenium failures: {selenium_metrics['selenium_failures']}")
    print(f"Request fallbacks used: {selenium_metrics['requests_fallbacks']}")
    print(f"Next button clicks automated: {selenium_metrics['next_button_clicks']}")
    print(f"Back Navigations atomated {selenium_metrics['back_navigations']}")
    print(f"Total unique products collected: {product_count}")
    print("-"*50)
    
    return productdict, product_count, selenium_metrics
            


warnings.filterwarnings('ignore', message='Unverified HTTPS request')
urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)

base_url = "https://scrapeme.live/shop/"

print("\n" + "-"*50)
print("Full Selenium Implementation")
print("-"*50)

productdict, product_count, selenium_metrics = scrape_pages_with_selenium(base_url, max_products=755)

if len(productdict['name']) > 0:
    scrapeme_df = pd.DataFrame(productdict)
    scrapeme_df.to_csv('ScrapeMe.csv', index=False)
    print(f"\n Saved {len(scrapeme_df)} products to 'ScrapeMe.csv'")

    if 'scraping_method' in scrapeme_df.columns:
        print("\n Scraping Methods Used:")
        methods = scrapeme_df['scraping_method'].value_counts()
        for method, count in methods.items():
            print(f" {method}: {count} products")

    print("\n Sample of scraped data")
    print(scrapeme_df[['name', 'price', 'scraping_method']].head())
else:
    print("\n No data collected")



# PokemonDB Scraping
def scrape_pokemondb():
    
    url = "https://pokemondb.net/pokedex/all"

    local_pokemondb_data = {
        'pokemon_name': [],
        'pokedex_number': [],
        'type1': [],
        'type2': [],
        'total_stats': [],
        'hp': [],
        'attack': [],
        'defense': [],
        'sp_attack': [],
        'sp_defense': [],
        'speed': []
    }

    try:
        time.sleep(2)

        response = requests.get(url, timeout=15)

        if response.status_code != 200:
            print(f"Failed to access PokemonDB: Status {response.status_code}")
            return create_fallback_stats()

        soup = BeautifulSoup(response.content, 'html.parser')

        # Locates the main Pokemon table
        table = soup.find('table', {'id': 'pokedex'})

        if not table:
            print("Could not find Pokemon table")
            return create_fallback_stats()

        rows = table.find_all('tr')[1:] # Skips header row
        unique_pokemon = set()
        filtered_rows = []

        # Filters for the first 50 unique Pokemon
        for row in rows:
            cells = row.find_all('td')
            if len(cells) >= 10:
                name_cell = cells[1].find('a', class_='ent-name')
                
                if name_cell:
                    pokemon_name = name_cell.text.strip()
                    if pokemon_name not in unique_pokemon:
                        unique_pokemon.add(pokemon_name)
                        filtered_rows.append(row)
                        if len(filtered_rows) >= 1025:
                            break

        print(f"Found {len(rows)} Pokemon in the table")

        # Extract the data from the filtered rows
        for i, row in enumerate(filtered_rows, 1):
            cells = row.find_all('td')

            if len(cells) >= 10:
                # Pokedex number
                num_cell = cells[0].find('span', class_='infocard-cell-data')
                pokedex_number = int(num_cell.text.strip() if num_cell else i)

                # Pokemon name
                name_cell = cells[1].find('a', class_='ent-name')
                pokemon_name = name_cell.text.strip() if name_cell else f"Pokemon{i}"

                # Elemental types
                type_cells = cells[2].find_all('a', class_='type-icon')
                type1 = type_cells[0].text.strip() if len(type_cells) > 0 else None
                type2 = type_cells[1].text.strip() if len(type_cells) > 1 else None

                # Total base stats
                total_stats = int(cells[3].text.strip()) if cells[3].text.strip().isdigit() else 0

                # Helper function that extracts numeric stats
                def get_stat(cell_index):
                    text = cells[cell_index].text.strip()
                    return int(text) if text.isdigit() else 0

                # Individual stat extraction
                hp = get_stat(4)
                attack = get_stat(5)
                defense = get_stat(6)
                sp_attack = get_stat(7)
                sp_defense = get_stat(8)
                speed = get_stat(9)

                # Append extracted data to the dictionary
                local_pokemondb_data['pokemon_name'].append(pokemon_name)
                local_pokemondb_data['pokedex_number'].append(pokedex_number)
                local_pokemondb_data['type1'].append(type1)
                local_pokemondb_data['type2'].append(type2)
                local_pokemondb_data['total_stats'].append(total_stats)
                local_pokemondb_data['hp'].append(hp)
                local_pokemondb_data['attack'].append(attack)
                local_pokemondb_data['defense'].append(defense)
                local_pokemondb_data['sp_attack'].append(sp_attack)
                local_pokemondb_data['sp_defense'].append(sp_defense)
                local_pokemondb_data['speed'].append(speed)

                print(f"Scraped: {pokemon_name} (#{pokedex_number}) - {type1}" + (f"{type2}" if type2 else "") + f" - Stats: {total_stats}")

        print(f"\n Successfully scraped {len(local_pokemondb_data['pokemon_name'])} pokemon")
        return local_pokemondb_data
            
    except Exception as e:
        print(f"Error scraping PokemonDB: {e}")
        return create_fallback_stats()



def create_fallback_stats():
    # Sample data
    fallback_pokemon = [
        ("Pikachu", 25, "Electric", None, 320, 35, 55, 40, 50, 50, 90),
        ("Charizard", 6, "Fire", "Flying", 534, 78, 84, 78, 109, 85, 100),
        ("Bulbasaur", 1, "Grass", "Poison", 318, 45, 49, 65, 65, 45),
        ("Squirtle", 7, "Water", None, 314, 44, 48, 65, 50, 64, 43),
        ("Mewtwo", 150, "Psychic", None, 680, 106, 110, 90, 154, 90, 130)
    ]

    fallback_data = {
        'pokemon_name': [],
        'pokedex_number': [],
        'type1': [],
        'type2': [],
        'total_stats': [],
        'hp': [],
        'attack': [],
        'defense': [],
        'sp_attack': [],
        'sp_defense': [],
        'speed': [],
    }

    for pokemon in fallback_pokemon:
        fallback_data['pokemon_name'].append(pokemon[0])
        fallback_data['pokedex_number'].append(pokemon[1])
        fallback_data['type1'].append(pokemon[2])
        fallback_data['type2'].append(pokemon[3])
        fallback_data['total_stats'].append(pokemon[4])
        fallback_data['hp'].append(pokemon[5])
        fallback_data['attack'].append(pokemon[6])
        fallback_data['defense'].append(pokemon[7])
        fallback_data['sp_attack'].append(pokemon[8])
        fallback_data['sp_defense'].append(pokemon[9])
        fallback_data['speed'].append(pokemon[10])
        
    return pd.DataFrame(fallback_data)


print("\n" + "-"*50)
print("STARTING POKEMONDB SCRAPING")
print("-"*50)

pokemon_stats_df = scrape_pokemondb()

if pokemon_stats_df is not None:
    if isinstance(pokemon_stats_df, dict):
        pokemon_stats_df = pd.DataFrame(pokemon_stats_df)

    if len(pokemon_stats_df) > 0:
        print(f"\n Collected {len(pokemon_stats_df)} Pokemon statistics")
        pokemon_stats_df.to_csv('pokemondb_stats.csv', index=False)
        print("Saved to 'pokemondb_stats.csv'")
        print("\nFirst 5 products")
        print(pokemon_stats_df.head(10))
    else:
        print("No Pokemon data found!")
else:
    print("Failed to scrape PokemonDB data") 


print("\n" + "-"*50)
print("Checking ScrapeMe Data")
print("-"*50)

print(f"Products scraped {len(productdict['name'])}")

if len(productdict['name']) > 0:
    scrapeme = pd.DataFrame(productdict)
    scrapeme.to_csv('ScrapeMe.csv', index=False)
    print(f"Saved {len(scrapeme)} products to 'ScrapeMe.csv'")
    print("\nFirst 5 products")
    print(scrapeme.head())
else:
    print("No products were scraped!")

print(f"\n{'-'*50}")
print(f"POKEMON SCRAPING COMPLETE")
print(f"{'-'*50}")
print(f"Collected {product_count} unique products")
print(f"Scraped {page_num-1} pages")

print(f"\nNames: {productdict['name']}")
print(f"\nPrices: {productdict['price']}")
print(f"\nDescription: {productdict['description']}")
print(f"\nStock: {productdict['stock']}")
print(f"\nSku: {productdict['sku']}")
print(f"\nCategories: {productdict['category']}")
print(f"\nTags: {productdict['tag']}")
print(f"\nWeight: {productdict['weight']}")
print(f"\nDimension: {productdict['dimension']}")