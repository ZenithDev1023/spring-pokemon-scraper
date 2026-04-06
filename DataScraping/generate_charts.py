import pandas as pd



df1 = pd.read_csv("pokemondb_stats.csv")

pokemon_name = df1["pokemon_name"].to_string()


typeGrass = []


for index, row in df1.iterrows():
    if row["type1"] == "Grass":
        typeGrass.append((row["pokemon_name"], row["type1"], row["total_stats"]))

print(typeGrass)



total_stats = df1["total_stats"].to_string()


df2 = pd.read_csv("ScrapeMe.csv")

name = df2["name"].to_string()
weight = df2["weight"].to_string()
tag = df2["tag"].to_string()

