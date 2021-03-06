package com.jruilibrary.form.layout.model;

/**
 * Created by zhush on 2017/3/14.
 * E-mail 405086805@qq.com
 * PS 属性容器
 */
public class AtrrContainer {
//    名称
  private String lessFormTitle = "标题";
//    是否显示底部横线
    private boolean lessFormBottomLine= true;
//    title右图片
    private int lessFormTitleImage = 0;
//    可以点击
    private boolean lessFormCanClick =false;
//    必须填写
    private boolean lessFormMust = false;
//    表单字段name
    private String lessFormName;
//    检查类型
    private int lessFormCheckType=0;
//    是否非空
    private boolean lessFormIsNull=false;
//    是否是表单组标题
    private boolean lessFormGroupTitel=false;
//    是否是 组布局
    private boolean lessFormGroupTopLayout=false;

    //右箭头
    private boolean rightArr=false;
    //是否禁用
    private boolean enabled=true;
    //是否禁用
    private boolean gone=false;

    public String getLessFormTitle() {
        return lessFormTitle;
    }

    public void setLessFormTitle(String lessFormTitle) {
        this.lessFormTitle = lessFormTitle;
    }

    public boolean getLessFormBottomLine() {
        return lessFormBottomLine;
    }

    public void setLessFormBottomLine(boolean lessFormBottomLine) {
        this.lessFormBottomLine = lessFormBottomLine;
    }

    public int getLessFormTitleImage() {
        return lessFormTitleImage;
    }

    public void setLessFormTitleImage(int lessFormTitleImage) {
        this.lessFormTitleImage = lessFormTitleImage;
    }

    public boolean getLessFormMust() {
        return lessFormMust;
    }

    public void setLessFormMust(boolean lessFormMust) {
        this.lessFormMust = lessFormMust;
    }

    public String getLessFormName() {
        return lessFormName;
    }

    public void setLessFormName(String lessFormName) {
        this.lessFormName = lessFormName;
    }

    public int getLessFormCheckType() {
        return lessFormCheckType;
    }

    public void setLessFormCheckType(int lessFormCheckType) {
        this.lessFormCheckType = lessFormCheckType;
    }

    public boolean getLessFormIsNull() {
        return lessFormIsNull;
    }

    public void setLessFormIsNull(boolean lessFormIsNull) {
        this.lessFormIsNull = lessFormIsNull;
    }

    public boolean getLessFormCanClick() {
        return lessFormCanClick;
    }

    public void setLessFormCanClick(boolean lessFormCanClick) {
        this.lessFormCanClick = lessFormCanClick;
    }

    public boolean getLessFormGroupTitel() {
        return lessFormGroupTitel;
    }

    public void setLessFormGroupTitel(boolean lessFormGroupTitel) {
        this.lessFormGroupTitel = lessFormGroupTitel;
    }

    public boolean getLessFormGroupTopLayout() {
        return lessFormGroupTopLayout;
    }

    public void setLessFormGroupTopLayout(boolean lessFormGroupTopLayout) {
        this.lessFormGroupTopLayout = lessFormGroupTopLayout;
    }

    public boolean getRightArr() {
        return rightArr;
    }

    public void setRightArr(boolean rightArr) {
        this.rightArr = rightArr;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getGone() {
        return gone;
    }

    public void setGone(boolean gone) {
        this.gone = gone;
    }
}
