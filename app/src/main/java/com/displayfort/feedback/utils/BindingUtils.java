
package com.displayfort.feedback.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.StreamEncoder;
import com.displayfort.feedback.R;
import com.displayfort.feedback.data.model.api.response.FeedBackResponse;
import com.displayfort.feedback.ui.feedback.OptionsAdapter;
import com.larvalabs.svgandroid.SVG;

import java.io.File;
import java.io.InputStream;
import java.util.List;


public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }


    @BindingAdapter({"adapter"})
    public static void addResponsibilityItems(RecyclerView recyclerView, List<FeedBackResponse.SubDao> genericList) {
        OptionsAdapter adapter = (OptionsAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(genericList, 1);
        }
    }

    public static void setImageUrl(ImageView imageView, String url, int mipmap) {
        if (url == null || url.length() <= 0 || url.equalsIgnoreCase("null")) {
            imageView.setImageResource(mipmap);
        } else {
            Context context = imageView.getContext();
            Glide.with(context)
                    .load(url)
                    .into(imageView);
        }
    }

    public static void setSVGImageUrl(ImageView imageView, String url, int mipmap) {
        Context context = imageView.getContext();

    }

    @BindingAdapter("id")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .into(imageView);
    }


    @BindingAdapter("id")
    public static void setImageFile(ImageView imageView, File url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }


    @BindingAdapter("id")
    public static void setImageUrlR(ImageView imageView, String url) {
        if (url != null && url.length() > 0) {
            Context context = imageView.getContext();
            setImageUrl(imageView, url);
        }
    }


    @BindingAdapter("id")
    public static void setImageFileR(ImageView imageView, File url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }
}
