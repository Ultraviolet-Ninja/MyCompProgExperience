n = int(input())

for i in range(n):
    string = input()

    if string[0] == string[len(string)-1]:
        if len(string) % 2 == 0:
            print("Chikapu")
        else:
            print("Bash")
    else:
        if len(string) % 2 == 0:
            print("Bash")
        else:
            print("Chikapu")