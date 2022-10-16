# get input
line = input()

# create dictionary/map
cards = {
    'T': 0,
    'C': 0,
    'G': 0
}

# loop through input and update dictionary values
for card in list(line):
    cards.update({card:cards[card] + 1})

# generate bonus points for having sets of each card.
minCount = min(cards.get('T'), cards.get('C'), cards.get('G')) * 7

# calculate final points by squaring number of each card and adding set bonus
print((pow(cards.get('T'), 2) + pow(cards.get('C'), 2) + pow(cards.get('G'), 2) + minCount))
