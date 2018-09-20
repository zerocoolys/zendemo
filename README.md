
Code solution

The solution was from a idea about dynamic programming.

To check if a string is valid, we can sperate the string to different parts,such as str[i]..str[j] and str[j+1]..str[l],
if both part is valid ,then the string str[i..l] is valid.


the formula is like:

**isValid(s,i,l) = isValid(s,i,j) && isValid(s,j+1,l)**

I store the temporal valid value TRUE to a matrix dp.
dp[i][l] consist just true or false

for input s , we need check if we can find a j which 
dp[0][j] && dp[j+1][l] == true

for example:

    :   )   (   )     1.initial dp     
    T   T   F   F     i = 0 => s[0] == ':' , s[0] == ':' && s[1] == ')'  
    F   T   F   F     set dp[0][0] == true, dp[0][1] = true, dp[1][1] == true
    F   F   F   T     
    F   F   F   F     i = 1 => no match pair
     
                      i = 2 => s[2] == '(' and s[3] == ')'
                      set dp[2][3] = true

    2. loop for the all possible string size which is (j) 
    between i and l
    
    :   )   (   )
    T                 j = 0 => isValid(s,0,3) = isValid(s,0,0) && isValid(s,1,3)
                F                           dp[0][0] && dp[1][3]
                
 
                
    :   )   (   )
    T   T             j = 1 => isValid(s,0,3) = isValid(s,0,1) && isValid(s,2,3)
        T       F                                  dp[0][1] && dp[2][3]
                T
                
   
    3. the final answer is dp[0][l] means isValid(s,0,l) , l is the length of input