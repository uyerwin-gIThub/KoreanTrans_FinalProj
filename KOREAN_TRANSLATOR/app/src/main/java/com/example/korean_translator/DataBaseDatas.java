package com.example.korean_translator;

public class DataBaseDatas {

     String EnglishWord;
     String HangulWord;
     String RomanizedWord;

     public DataBaseDatas(){
     }

    public DataBaseDatas(String EnglishWord, String HangulWord, String RomanizedWord){
        this.EnglishWord = EnglishWord;
        this.HangulWord = HangulWord;
        this.RomanizedWord = RomanizedWord;
    }

    public String getEnglishWord()
    {
        return this.EnglishWord;
    }

    public String getHangulWord()
    {
        return this.HangulWord;
    }

    public String getRomanizedWord()
    {
        return this.RomanizedWord;
    }

    public void setEnglishWord(String englishWord)
    {
        this.EnglishWord = englishWord;
    }

    public void setHangulWord(String hangulWord)
    {
        this.HangulWord = hangulWord;
    }

    public void setRomanizedWord(String romanizedWord)
    {
        this.RomanizedWord = romanizedWord;
    }

}