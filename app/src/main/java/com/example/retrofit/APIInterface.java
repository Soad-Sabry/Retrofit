package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    /*
    @GET("posts/{id}")
    public Call<List<Post>> getPost(@Path("id") int postId);

    //   public Call<Post> getPost();

    @POST("posts")
    public Call<Post>storePost(@Body Post post);


     */
    @GET("Posts")
    Call<List<Post>> getPosts();

}
