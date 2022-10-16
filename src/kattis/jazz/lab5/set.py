# Bilal Syed, Malcolm Johnson, Jarred Kohout

from itertools import combinations


# representation of a given card
class Card:
    def __init__(self, color, shape, fill, number, location):
        self.color = color
        self.shape = shape
        self.fill = fill
        self.number = number
        self.location = location


# count the number of traits that are the same among all cards in the set
def num_same(card1, card2, card3):
    count = (card1.color == card2.color == card3.color) + \
               (card1.shape == card2.shape == card3.shape) + \
               (card1.fill == card2.fill == card3.fill) + \
               (card1.number == card2.number == card3.number)
    return count


# count the number of traits that are pairwise different among all cards in the set
def num_diff(card1, card2, card3):
    count = (card1.color != card2.color and card2.color != card3.color and card1.color != card3.color) + \
               (card1.shape != card2.shape and card2.shape != card3.shape and card1.shape != card3.shape) + \
               (card1.fill != card2.fill and card2.fill != card3.fill and card1.fill != card3.fill) + \
               (card1.number != card2.number and card2.number != card3.number and card1.number != card3.number)
    return count


# determine which sets are valid
def analyze(combs):
    # keep track of valid sets
    sets = []
    for comb in combs:
        number_same = num_same(*comb)
        number_different = num_diff(*comb)
        # set is valid if each of the 4 traits are valid, whether that means they
        # are the same among cards in the set or pairwise different
        valid = number_same + number_different == 4
        if valid:
            # sets should be a list of tuples to allow easy sorting
            sets.append([*(p.location for p in comb)])

    if not sets:
        print('no sets')
        exit()
    # sets need to be in sorted order
    for card_set in sorted(sets):
        # print numbers space separated
        print(*card_set)


# solve one game of Set!
def main():
    location = 1
    cards = []
    # parse and create card representations
    for _ in range(4):
        card_strings = input().split()
        for card_string in card_strings:
            number = card_string[0]
            shape = card_string[1]
            fill = card_string[2]
            color = card_string[3]
            card = Card(color, shape, fill, number, location)
            cards.append(card)
            location += 1
    # get all combinations of 3 cards
    combs = combinations(cards, 3)
    analyze(combs)


if __name__ == '__main__':
    main()
