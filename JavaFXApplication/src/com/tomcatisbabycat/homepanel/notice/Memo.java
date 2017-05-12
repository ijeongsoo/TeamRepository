
package com.tomcatisbabycat.homepanel.notice;

public class Memo {
    
    private String contents;
    private String date;
    
    
    public Memo(String date, String contents){
          this.contents = contents;
          this.date = date;
          
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
