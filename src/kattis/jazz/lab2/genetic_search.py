

def get_count(word, sentence):
    """Get the number of times the word (subsequence) is found in the sentence (sequence). Uses naive string search.

    :param word: word (or subsequence)
    :param sentence: sentence (or sequence)
    :return: number of times the word is found in the sentence.
    """
    len_word = len(word)
    count = 0
    for i in range(len(sentence) - len_word + 1):
        if sentence[i:i+len_word] == word:
            count += 1
    return count


def get_counts(subs, seq):
    """Call get_count() with the subsequence and the sequence for each subsequence in the given set

    :param subs: set of subsequences
    :param seq: sequence
    :return: total number of times the subsequences are found in the sequence
    """
    count = 0
    for sub in subs:
        count += get_count(sub, seq)
    return count


def get_shorter_subsequences(sub):
    """Get group 2 given the subsequence, where group 2 is all unique subsequences with a character removed

    :param sub: subsequence string
    :return: set of subsequences
    """
    subs = set()
    for i in range(len(sub)):
        subs.add(sub[:i] + sub[i+1:])
    return subs


def get_longer_subsequences(sub):
    """Get group 3 given the subsequence, where group 3 is all unique subsequences with a character inserted

    :param sub: subsequence string
    :return: set of subsequences
    """
    subs = set()
    for letter in ['A', 'G', 'C', 'T']:
        for i in range(len(sub)+1):
            subs.add(sub[:i] + letter + sub[i:])
    return subs


def print_subsequence_counts(sub, seq):
    """Take a subsequence string and a sequence string, and derive the groups of subsequences. For each
    group, get the counts for that group and print all to the console at the end.

    :param sub: subsequence - string to search for
    :param seq: sequence - "container" string
    """
    # group 1 - just the given subsequence
    count_1 = get_count(sub, seq)
    # group 2 - all unique subsequences with a character removed
    subs_2 = get_shorter_subsequences(sub)
    count_2 = get_counts(subs_2, seq)
    # group 3 - all unique subsequences with a character inserted
    subs_3 = get_longer_subsequences(sub)
    count_3 = get_counts(subs_3, seq)

    print(count_1, count_2, count_3)


def main():
    """Parse the input (subsequence then sequence) line by line until a '0' is read
    """
    line_in = input()
    while line_in != '0':
        sub, seq = line_in.split()
        print_subsequence_counts(sub, seq)
        line_in = input()


if __name__ == '__main__':
    main()
