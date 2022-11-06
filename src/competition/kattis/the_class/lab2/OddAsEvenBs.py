import itertools

# generate the sequences of letters based on how many we want
def string_of_num_letters(num, letter):
    string = ""
    for i in range(num):
        string += letter
    return string

# function used to filter out the combinations we don't care about like ones that go over our 
# input length or are just straight up invalid
def filter_not_matching_sequences(sequence):
    count = 0

    # remove any empty spots
    sequence = list(sequence)
    while ("" in sequence):
        sequence.remove("")

    # the sequence should go a's then b's and repeat so we need to check that its true
    last_a = False
    if len(sequence) > 0 and sequence[0] == 'b':
        last_a = True

    for letter in sequence:
        count += len(letter)
        if last_a:
            # if we have an odd number of a's followed by another set of 
            # odd number of a's then it becomes an even number of a's and is now invalid
            if letter[0] == 'a':
                return False
            else:
                last_a = False
        else:
            # an even number of b's followed by another set of even b's is still even so actually isn't invalid
            if letter[0] == 'b':
                last_a = False
            else:
                last_a = True

    # check the count, if its not the input length then we don't care for it
    if count == input_length:
        return True
    else:
        return False

# all combinations must be at the length of the input_length, no more no less
input_length = int(input())

# this array will store all the sets of odd a's and b's we will need to generate the combinations
letter_seqiences = []
letter_seqiences.append('')

# add all sets of a's and b's we will need
for i in range(1, input_length+1):
    if i % 2 == 0:
        letter_seqiences.append(string_of_num_letters(i, 'b'))
    else:
        letter_seqiences.append(string_of_num_letters(i, 'a'))

# use itertools to create all possible combinations using the sets of a's and b's we have
combinations = list(itertools.product(letter_seqiences, repeat=4))

# filter that result to match our requirements
total_combos = list(filter(filter_not_matching_sequences, combinations))

#join the tuples so they are strings and remove duplicates
final_combos = []
for combos in total_combos:
    combos = list(combos)
    final_combos.append(''.join(combos))

final_combos = list(set(final_combos))
# print out the number of combinations
print(len(final_combos))


