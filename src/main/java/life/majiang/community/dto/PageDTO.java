package life.majiang.community.dto;

import java.util.ArrayList;
import java.util.List;

public class PageDTO {
    private List<QuestionDTO> questions;
    private boolean hasPrevious;
    private boolean hasFirstPage;
    private boolean hasNext;
    private boolean hasEndPage;

    private Integer page;
    private Integer totalPage;
    private List<Integer> pages = new ArrayList<>();


    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public boolean isHasFirstPage() {
        return hasFirstPage;
    }

    public void setHasFirstPage(boolean hasFirstPage) {
        this.hasFirstPage = hasFirstPage;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasEndPage() {
        return hasEndPage;
    }

    public void setHasEndPage(boolean hasEndPage) {
        this.hasEndPage = hasEndPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public void setPage(Integer count, Integer page, Integer size) {

        this.totalPage=(count/size)==0?(count/size):(count/size+1);

        if(page<1){
            page=1;
        }
        if (page>totalPage){
            page = totalPage;
        }

        this.page=page;

        hasPrevious=page!=1;
        hasNext=(page==totalPage)?false:true;

        pages.add(page);
        for (int i = 1 ; i <= 3 ;i++){
           if (page-i>0){
               pages.add(0,page-i);
           }
           if (page+i<=totalPage){
               pages.add(page+i);
           }
        }
        hasFirstPage=pages.contains(1)?false:true;
        hasEndPage=pages.contains(totalPage)?false:true;
    }
}
