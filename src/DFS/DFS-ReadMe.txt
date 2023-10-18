DFS:
1. Permutation --> Swap the int i and int index, in the DFS recursive call, using index + 1 as the new parameter
2. Combination --> duplicates is not allowed, i start from [0 - n), element starts from (i + 1) --> [1, n], in the DFS recursive call, using i + 1 as the new parameter
3. Combination Sum --> duplicates are allowed, i start from index, in the DFS recursive call, using i as the new parameter. Base case: if (target == 0) addedUp & return, else if (target < 0) return;
4. Factor Combination --> use a linkedList, i from 2 (if list is empty) to square root of factor[2, lastFactor / i], Base case: list.size() == k
                                                            
