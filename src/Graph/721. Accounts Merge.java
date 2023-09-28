/*
721. Accounts Merge.java
https://leetcode.com/problems/accounts-merge/

Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. 
Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, 
but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order.
The accounts themselves can be returned in any order.

Example 1:
Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Explanation:
The first and second John's are the same person as they have the common email "johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Example 2:
Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],
["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],
["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
Constraints:

1 <= accounts.length <= 1000
2 <= accounts[i].length <= 10
1 <= accounts[i][j].length <= 30
accounts[i][0] consists of English letters.
accounts[i][j] (for j > 0) is a valid email.
*/

// Abstraction:
// Combine all accounts owned by a same person into him/her.

// Methodology:
// Use DFS with AdjList.
// Due to the fact that the person's name can be duplicated, but the email address will be unique
// So to use email address as the key in both of the hashSet, hashMap.

class Solution {
    HashSet<String> visited = new HashSet<>();
    Map<String, List<String>> adjListMap = new HashMap<>();

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        // Building the adjList map
        for (List<String> account : accounts) {
            int accountSize = account.size();

            String accountFirstEmail = account.get(1);
            // adding the rest of emails relationship into the adjListMap
            for (int j = 2; j < accountSize; j++) {
                String accountEmail = account.get(j);

                if (!adjListMap.containsKey(accountFirstEmail)) {
                    adjListMap.put(accountFirstEmail, new ArrayList<>());
                }
                adjListMap.get(accountFirstEmail).add(accountEmail);

                if (!adjListMap.containsKey(accountEmail)) {
                    adjListMap.put(accountEmail, new ArrayList<>());
                }
                adjListMap.get(accountEmail).add(accountFirstEmail);
            }
        }

        // Traverse over the adjListMap
        List<List<String>> res = new ArrayList<>();
        for (List<String> account : accounts) {
            String accountName = account.get(0);
            String accountFirstEmail = account.get(1);

            // if email is visited, then it is a part of different component
            if (!visited.contains(accountFirstEmail)) {
                List<String> mergedAccount = new ArrayList<>();
                // Adding account name at the 0th index
                mergedAccount.add(accountName);
                dfs(mergedAccount, accountFirstEmail);
                Collections.sort(mergedAccount.subList(1, mergedAccount.size()));
                res.add(mergedAccount);
            }
        }
        return res;
    }

    private void dfs(List<String> mergedAccount, String email) {
        visited.add(email);
        mergedAccount.add(email);
        // If this condition is true, it means that there are no further emails connected to the current email, and therefore, there's no need to perform any more recursive DFS (Depth-First Search) calls for this particular email
        if (!adjListMap.containsKey(email)) {
            return;
        }

        for (String nei : adjListMap.get(email)) {
            if (!visited.contains(nei)) {
                dfs(mergedAccount, nei);
            }
        }
    }
}

/*
accounts = [
["John","johnsmith@mail.com","john_newyork@mail.com"],
["John","johnsmith@mail.com","john00@mail.com"],
["Mary","mary@mail.com"],
["John","johnnybravo@mail.com"]]

Output: [
["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"]
["Mary","mary@mail.com"],
["John","johnnybravo@mail.com"]]
*/
