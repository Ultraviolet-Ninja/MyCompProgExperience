

def main():
    """Parse input to get n, a, b, and c, and iterate through the ride lists, applying the functions
    A(n) = b*B(n-1) + c*C(n-1)
    B(n) = a*A(n-1) + c*C(n-1)
    C(n) = a*A(n-1) + b*B(n-1)
    to populate the lists and then the function
    T(n) = a*A(n-1) + b*B(n-1) + c*C(n-1)
    to accumulate the total.
    """
    n, a, b, c = (int(x) for x in input().split())
    lists = {'a': [a], 'b': [b], 'c': [c]}
    totals = [a + b + c]

    # build up arrays of number of strings ending in each letter
    for i in range(n-1):
        lists['a'].append(b * lists['b'][i] + c * lists['c'][i])
        lists['b'].append(a * lists['a'][i] + c * lists['c'][i])
        lists['c'].append(a * lists['a'][i] + b * lists['b'][i])
        totals.append(a * lists['a'][i] + b * lists['b'][i] + c * lists['c'][i])

    # print the final total as the answer
    print(totals[-1])


if __name__ == '__main__':
    main()
