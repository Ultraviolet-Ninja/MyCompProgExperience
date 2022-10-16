# partition method for quicksort
def partition(l, r, nums):
    pivot, ptr = nums[r], l
    for i in range(l, r):
        if nums[i] <= pivot:
            nums[i], nums[ptr] = nums[ptr], nums[i]
            ptr += 1
    nums[ptr], nums[r] = nums[r], nums[ptr]
    return ptr


# quicksort implementation
def quicksort(l, r, nums):
    if len(nums) == 1:
        return nums
    if l < r:
        pi = partition(l, r, nums)
        quicksort(l, pi - 1, nums)
        quicksort(pi + 1, r, nums)
    return nums


# standard binary search implementation
def binary_search(arr, low, high, x):
    # Check base case
    if high >= low:

        mid = (high + low) // 2

        # If element is present at the middle itself
        if arr[mid] == x:
            return mid

        # If element is smaller than mid, then it can only
        # be present in left subarray
        elif arr[mid] > x:
            return binary_search(arr, low, mid - 1, x)

        # Else the element can only be present in right subarray
        else:
            return binary_search(arr, mid + 1, high, x)

    else:
        # Element is not present in the array
        return -1


# In the case of duplicate values when looking for max boundary find the furthest right value
def find_max(index, cards):
    if index == len(cards) - 1:
        return index
    while cards[index] == cards[index + 1]:
        if index + 1 == len(cards) - 1:
            return index + 1
        index += 1
    return index


# In the case of duplicate values when looking for min boundary find the furthest left value
def find_min(index, cards):
    if index == 0:
        return index
    while cards[index] == cards[index - 1]:
        if index - 1 == 0:
            return index - 1
        index -= 1
    return index


# get input and store into list
numberOfCards = int(input())
cards = [int(x) for x in input().split()]
# sort list
cards = quicksort(0, len(cards) - 1, cards)
numberOfRanges = int(input())

# loop through ranges
for ranges in range(numberOfRanges):
    # get the range
    single_range = [int(x) for x in input().split()]

    # if max is less than smallest card or min is greater than largest card then no cards could fit so just print 0
    if single_range[0] > cards[len(cards) - 1]:
        print("0")
    elif single_range[1] < cards[0]:
        print("0")
    else:
        # some edge cases
        # if our min is smaller than the smallest card just set it to smallest card val
        if single_range[0] < cards[0]:
            single_range[0] = cards[0]
        # if max is larger than largest card just set it to largest card val
        if single_range[1] > cards[len(cards) - 1]:
            single_range[1] = cards[len(cards) - 1]
        # if the min boundary does not exist find the nearest value that works
        if single_range[0] not in cards:
            # find nearest value to binary search for
            single_range[0] = min([i for i in cards if i >= single_range[0]], key=lambda x: abs(x - single_range[0]))
        # binary search for that value
        searchResult = binary_search(cards, 0, len(cards) - 1, single_range[0])
        # if duplicates get the one furthest to the left
        index1 = find_min(searchResult, cards)

        # if the max boundary does not exist find the nearest value that works
        if single_range[1] not in cards:
            # find nearest value to binary search for
            single_range[1] = min([i for i in cards if i < single_range[1]], key=lambda x: abs(x - single_range[1]))
        # binary search for that value
        searchResult = binary_search(cards, 0, len(cards) - 1, single_range[1])
        # if duplicates get the one furthest to the right
        index2 = find_max(searchResult, cards)

        # if the ranges are in a situation where min is larger than max then nothing can fit so return 0
        if index1 > index2:
            print("0")
        else:
            # use indexes to find how many numbers in the range
            print(index2 - index1 + 1)
