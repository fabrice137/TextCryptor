/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textcryptor;

/**
 *
 * @author fabruz
 */
public class cryptoFunction {
    
    
    public static String boosted(String word){
        /** FOR WORDS THAT ARE LESS THAN TWELVE CHARS ERROR ARE FREQUENT SO MAKE THEM LONGER */
        int sz = word.length();
        int ratio = 12-sz;
        for(int i=0 ; i<ratio ; i++){
            word += " ";
        }

        return word;
    }

    public static int[] turn2Dec(String x){     // returns an int array from a word's character values
        int[] decims = new int[x.length()];
        for(int i=0 ; i<x.length() ; i++) decims[i] = (int)x.charAt(i);
        return decims;
    }

    public static String twoletter(int x){
        //   alphabet scrabbled first odds then evens
        char[] alphabet = new char[26];
        /* alphabet = m l k j i h g f e d c b a z y x w v u t s r q p o n  */
        for(int i=0 ; i<26 ; i++){
            alphabet[i] = (char)(109 - i);
            if(i>12) alphabet[i] = (char)(135 - i);
        }
        // THE MOD CAN BE ANY NUMBER BTWN 0 AND 25 TO MATCH ALPHABET  BUT THE DIV CAN ONLY BE SO SMALL
        // THEN WHEN MOD IS 13 OR GREATER THE NEXT LETTER WILL BE <= DIV LETTERS IN THAT DIRECTION
        // WHEN MOD IS 12 OR LESS THE NEXT LETTER WILL BE => DIV LETTERS IN THAT DIRECTION.
        int div = x / 26;     int mod = x % 26;
        int next = mod + div;    if(mod > 12) next = mod - div;
        String sol = "" + alphabet[mod] + alphabet[next] + "";
        return sol;
    }

    public static String makeWord(String[] arr){
        //  Taking-in an array of characters and returning a string/word.
        //  the word follows the sequence of the array.
        String word = "";
        for(int i=0 ; i<arr.length ; i++) word += arr[i] + "";
        return word;
    }
    
    public static String makeWord(char[] arr){
        //  Taking-in an array of characters and returning a string/word.
        //  the word follows the sequence of the array.
        String word = "";
        for(int i=0 ; i<arr.length ; i++) word += arr[i];
        return word;
    }
    
    public static String random1st(String word_string, int do_OR_undo){
        // do_OR_undo to do(positive) and remove(negative) the effect
        // ADD A RANDOM CHARACTER OR TWO BEFORE THE WORD_STRING
        char[] alphabet = new char[26];
        for(int i=0 ; i<26 ; i++) alphabet[i] = (char)(97 + i);

        if(do_OR_undo > 0){
            int Random = (int)(Math.random()*26);
            String wordRandom = "";
            if(Random < 13) wordRandom = alphabet[Random] + "";
            if(Random > 12) wordRandom = alphabet[Random] + "" + alphabet[Random] + "";
            word_string = wordRandom + word_string;
        }
        else {
            if(((int)word_string.charAt(0)) < 110) word_string = word_string.substring(1) ;
            if(((int)word_string.charAt(0)) > 109) word_string = word_string.substring(2) ;
        }

        return word_string;
    }

    public static String smarten(String word, int do_OR_undo){
        // do_OR_undo to do(positive) and remove(negative) the effect
        // TO SCRABBLE WORDS THAT THEY ARE TIED TO THEIR POSITION IN THE STRING
        // in AARON (THE 1st A HAS A DIFFERENT LETTER FROM THE 2nd A
        // PLUS A RANDOM CHARACTER AT THE BEGINNIG OF THE WORD  WITH ITS INT AS RANDOM

        if(do_OR_undo > 0) word = random1st(word, do_OR_undo);

        int random = (int)word.charAt(0);

        String neWord = word.charAt(0) + "";

        for(int i=1 ; i<word.length() ; i++){
            neWord += shuffleCHAR(word.charAt(i), i, random,do_OR_undo) + "";
        }
        if(do_OR_undo < 0 && random < 110) neWord = neWord.substring(1);
        if(do_OR_undo < 0 && random > 109) neWord = neWord.substring(2);
        return neWord;
    }

    public static char shuffleCHAR(char kar, int position, int random,int do_OR_undo){
        // do_OR_undo to do(positive) and remove(negative) the effect
        // TO SHUFFLE CHARACTERS CONSIDERING THEIR POSITION
        // THE do_OR_undo IS FOR REVERSING THE EFFECT NEGATIVE VS POSITIVE
        char[] alphabet = new char[26];
        for(int i=0 ; i<26 ; i++) alphabet[i] = (char)(97 + i);

        int adder = random ;
        if(do_OR_undo < 0) adder = -random;

        for(int i=0 ; i<26 ; i++){
            if(kar == alphabet[i]) {
                adder += i;
                break;
            }
        }
        int newPosition = 13 + adder + position ;
        while(newPosition > 25)newPosition = newPosition - 26;
        // THE REVERSER STARTS HERE
        if(do_OR_undo < 0) {
            newPosition = adder - (13 + position);
            while(newPosition < 0)newPosition = newPosition + 26;
        }
        char finkar = alphabet[newPosition];

        return finkar;
    }

    public static String[] believable(String line){

        int szl = line.length();     int spaces = szl / 5;
//          if(szl<25) spaces = 3;

        int[] wordsizes = randoms2equal(szl, spaces, 9);

        int spcAt = 0;
        String[] lineout = new String[spaces + 1];

        for(int i=0 ; i<spaces ; i++){
            lineout[i] = line.substring(spcAt, spcAt + wordsizes[i]);
            spcAt += wordsizes[i];
        }
        lineout = addOnEach(11, " ", lineout);

        String[] finArr = new String[lineout.length - 1];
        System.arraycopy(lineout, 0, finArr, 0, lineout.length - 1);
        return finArr;
    }

    public static void Andika(String x){     // writes a string like System.out.println
        System.out.println(x);
    }

    public static int[] randoms2equal(int number, int arr_size, int range){
        // to create an array of random numbers of the size arr_size and each number
        // not exceding the range you want but not less than 1. THEIR SUM EQUAL NUMBER

        int[] numArr = new int[arr_size];
        int k=0;
        while(k < arr_size)                               //  while(k < sz)      while(numb - status <= 8)
        {
            int gen = 1 + (int) (Math.random() * range);
            numArr[k] = gen;
            k++;
        }
        int diff = number - sumarray(numArr);  // calculate the difference btw numb & arraysum up to now
        int i = 0;
        while(diff != 0){
            if(diff < 0){
                if(numArr[i] > 4) numArr[i] -= 1;
            }
            else if(diff > 0){
                if(numArr[i] < 6) numArr[i] += 1;
            }
            diff = number - sumarray(numArr);
            i++;
            if(i == arr_size) i = 0;  // the loop starts over !!
        }
        return numArr;
    }
    
    public static int sumarray(int[] arr){
        //  to calculate the total of all integer added together
        int tot = 0;
        for(int i=0 ; i<arr.length ; i++) tot += arr[i];
        return tot;
    }

    public static String[] addOnEach(int x, String xxx, String[] arrlist){
        //  adding a string or word on each of the element of an array
        //  the number x=0 the addon is at the start ; x>0 or smthng else the addon goes behind
        int n = arrlist.length;
        String[] done = new String[n];
        if(x == 0){
            for(int i=0 ; i<n ; i++) done[i] = xxx + arrlist[i];
        }
        else for(int i=0 ; i<n ; i++) done[i] = arrlist[i] + xxx;
        return done;
    }

    public static String[] line2Arr(String phrase){
        // you enter a phrase and it turns it into an array of individual words.

        phrase = " " + phrase + " ";  // adding spaces both at the begining and end of phrase to avoid errors
        int szP = phrase.length();   // szP = size of the phrase
        
        java.util.ArrayList<Integer> startNAend = new java.util.ArrayList<>(); 
        int count1 = 0;

        for(int i=1 , j=i ; i<szP-1 ; i++, j++) {
            // COUNT THE START OF EACH WORD 
            if(phrase.charAt(i) != ' ' && phrase.charAt(i-1) == ' '){
                startNAend.add(i);
                count1++;
            }
            // COUNT THE END OF EACH WORD 
            if(phrase.charAt(j) != ' ' && phrase.charAt(j+1) == ' ') {
                startNAend.add(j+1);
                count1++;
            }
        }
        
        //  now I can start to make words as i know where they start and where they end!!
        String[] finArr = new String[count1/2];   
        int count2 = 0;

        for(int i=0 ; i<count1 ; i++){
            if(i%2 == 0) {
                finArr[count2] = phrase.substring(startNAend.get(i), startNAend.get(i+1));
                count2++;
            }
        }

        return finArr;
    }


    public static int UN2letter(String two){
        // remove the effect of two letter
        char[] alphabet = new char[26];
        /* alphabet = m l k j i h g f e d c b a z y x w v u t s r q p o n  */
        for(int i=0 ; i<26 ; i++){
            alphabet[i] = (char)(109 - i);
            if(i>12) alphabet[i] = (char)(135 - i);
        }

        int mod = 0;     int next = 0;
        for(int i=0 ; i<26 ; i++) {
            if(two.charAt(0) == alphabet[i]) mod = i;
            if(two.charAt(1) == alphabet[i]) next = i;
        }
        int div = next - mod;  if(mod > 12) div = mod - next;
        int sol = (26 * div) + mod;

        return sol;
    }


    public static char[] turn2Char(int[] arr){      // returns an int array's character value array
        char[] chars = new char[arr.length];
        for(int i=0 ; i<arr.length ; i++) chars[i] = (char)arr[i];
        return chars;
    }


    public static String un_remixer(String x){
        // undoing the effect of remixer
        int n = x.length();   String mot = "";

        String xmot = "";   String ymot = "";
        for(int i=0 ; i<n ; i++) {
            if(i%2 == 0) xmot += x.charAt(i);
            else ymot += x.charAt(i);
        }
        mot = ymot + xmot;

        return mot;
    }
    
    public static String remixer(String word){
        //  mixing starting from the centre of the word ugenda usimbagurika
        //  ujya imbere ukongera ukajya inyuma.

        int n = word.length()/2;
        String one = word.substring(0, n); String two = word.substring(n);
        String mot = "";
        for(int i=0 ; i<n ; i++) mot += "" + two.charAt(i) + one.charAt(i);
        if(word.length()%2 != 0) mot += two.charAt(n);

        return mot;
    }
    

    
}
