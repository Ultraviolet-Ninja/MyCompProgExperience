# Bilal Syed, Malcolm Johnson, Jarred Kohout

from collections import deque


# return whether it is possible to make a move on the given board
def move_possible(board):
    for idx, letter in enumerate(board):
        if letter == 'o':
            if idx - 2 >= 0 and board[idx - 1] == 'o' and board[idx - 2] == '-':
                return True
            if idx + 2 <= (len(board) - 1) and board[idx + 1] == 'o' and board[idx + 2] == '-':
                return True
    # if no pieces can move, no moves are possible
    return False


# return number of pieces in the board
def count_os(board):
    return sum(x == 'o' for x in board)


# search through game endings by iteratively adding boards with valid moves to a queue and popping from the
# queue until empty
def play_game(board):
    # start with initial board
    games = deque([list(board)])
    # worst case scenario is that no moves are possible
    minimum_os = count_os(board)
    while games:
        board = games.pop()
        if move_possible(board):
            # add all valid moves to queue
            for idx, letter in enumerate(board):
                if letter == 'o':
                    # look at left jump move
                    if idx - 2 >= 0 and board[idx - 1] == 'o' and board[idx - 2] == '-':
                        # make a new board with left jump move performed
                        new_board = board.copy()
                        new_board[idx - 2] = 'o'
                        new_board[idx] = '-'
                        new_board[idx - 1] = '-'
                        games.appendleft(new_board)
                    # look at right jump move
                    if idx + 2 <= (len(board) - 1) and board[idx + 1] == 'o' and board[idx + 2] == '-':
                        # make a new board with right jump move performed
                        new_board = board.copy()
                        new_board[idx + 2] = 'o'
                        new_board[idx] = '-'
                        new_board[idx + 1] = '-'
                        games.appendleft(new_board)
        else:
            # end of the line for this board
            num_os = count_os(board)
            if num_os < minimum_os:
                minimum_os = num_os
    print(minimum_os)


# find best final board for each starting game
def main():
    num_games = int(input())
    for _ in range(num_games):
        play_game(input())


if __name__ == '__main__':
    main()
