package com.anukul.paginglibrarydemo;


import java.util.List;

class OwnerModel {
    public int reputation;
    public long user_id;
    public String user_type;
    public String profile_image;
    public String display_name;
    public String link;

}
class ItemModel {
    public OwnerModel owner;
    public boolean is_accepted;
    public int score;
    public long last_activity_date;
    public long last_edit_date;
    public long creation_date;
    public long answer_id;
    public long question_id;

}

public class StackApiResponseModel {
    public List<ItemModel> items; 
    public boolean has_more;
    public int quota_max;
    public int quota_remaining;

}
