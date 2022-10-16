# Jarred Kohout, Bilal Syed, Malcolm Johnson

from sys import stdin
from collections import deque
import heapq

# guess the data structure for the next length lines
def determine_data_structure(length):
    # initialize data structures
    stack = []
    queue = deque()
    priority_queue = []
    num_elements = 0

    # assume all are possible to start
    possible_datastructures = {'stack', 'queue', 'priority queue'}

    impossible = False

    for _ in range(length):
        next_line = next(stdin)
        if not impossible:
            action, value = (int(x) for x in next_line.split())
            if action == 1:  # add to bag
                stack.append(value)
                queue.appendleft(value)
                heapq.heappush(priority_queue, -value)
                num_elements += 1
            else:  # remove from bag
                # if we try to remove from empty bag, it is impossible
                if num_elements == 0:
                    impossible = True
                    continue

                # pop from each data structure to determine possibility 
                
                if 'stack' in possible_datastructures:
                    stack_value = stack.pop()
                    if stack_value != value:
                        possible_datastructures.remove('stack')

                if 'queue' in possible_datastructures:
                    queue_value = queue.pop()
                    if queue_value != value:
                        possible_datastructures.remove('queue')

                if 'priority queue' in possible_datastructures:
                    p_queue_value = -heapq.heappop(priority_queue)
                    if p_queue_value != value:
                        possible_datastructures.remove('priority queue')

                num_elements -= 1

    # print out result
    if len(possible_datastructures) == 1:
        print(possible_datastructures.pop())
    elif len(possible_datastructures) < 1 or impossible:
        print('impossible')
    else:
        print('not sure')


def main():
    # loop through stdin until EOF
    try:
        while True:
            line = next(stdin)
            length = int(line)
            determine_data_structure(length)
    except StopIteration:
        return


if __name__ == '__main__':
    main()
