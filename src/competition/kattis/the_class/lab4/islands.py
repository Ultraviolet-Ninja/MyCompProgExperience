# Jarred Kohout, Bilal Syed, Malcolm Johnson

from collections import deque


# quick helper function to check bounds of a cell
def in_grid(cell, x, y):
    return 0 <= cell[0] < x and 0 <= cell[1] < y


# explore an island which is a stretch of connected Ls and Cs (however it must start with an L)
def explore_island(grid, x, y, cell, searched):
    # perform a naive search starting on the given cell
    island = set()
    frontier = deque()
    frontier.appendleft(cell)
    searched.add(cell)
    while len(frontier) > 0:
        cell = frontier.pop()
        island.add(cell)
        i = cell[0]
        j = cell[1]
        left = (i-1, j)
        right = (i+1, j)
        up = (i, j-1)
        down = (i, j+1)
        for neighbor in (left, right, up, down):
            # add (valid) neighbor cell that hasn't been searched to island if it is an L or a C
            if in_grid(neighbor, x, y) and neighbor not in searched and grid[neighbor[0]][neighbor[1]] in ['L', 'C']:
                # add to searched so we don't re-explore the same land
                searched.add(neighbor)
                frontier.appendleft(neighbor)


# print out the number of possible islands in the grid
def analyze(grid, x, y):
    num_islands = 0
    searched = set()
    for i in range(x):
        for j in range(y):
            cell = (i, j)
            # if cell is in searched, it already belongs to an island, so we skip it
            # only start exploring an island on visible land
            if cell not in searched and grid[i][j] == 'L':
                # do search on cell to explore island
                explore_island(grid, x, y, cell, searched)
                num_islands += 1

    print(num_islands)


# main - run for one grid
def main():
    height, width = (int(x) for x in input().split())
    grid = [[] for _ in range(width)]
    # populate grid
    for h in range(height):
        line = input()
        for idx, i in enumerate(line):
            grid[idx].append(i)

    # access cells with grid[x][y], or grid[width][height]

    # determine and print number of possible islands
    analyze(grid, width, height)


if __name__ == '__main__':
    main()
