# Bilal Syed, Malcolm Johnson, Jarred Kohout

# parse and analyze games until a zero is encountered
def main():
    # system will exit when a '0' is read in
    while True:
        keep_looping = True
        # minimum and maximum define our range of certainty
        minimum = 0
        maximum = 11
        while keep_looping:
            guess = int(input())
            if guess == 0:
                exit()
            response = input()
            if response == 'right on':
                if minimum < guess < maximum:
                    # guess is in appropriate range
                    print('Stan may be honest')
                else:
                    # guess is impossible if stan was honest
                    print('Stan is dishonest')
                keep_looping = False
            elif response == 'too high':
                # we know the number must be lower than or equal to the guess and our current maximum
                maximum = min(guess, maximum)
            else:
                # we know the number must be higher than or equal to the guess and our current minimum
                minimum = max(guess, minimum)


if __name__ == '__main__':
    main()
