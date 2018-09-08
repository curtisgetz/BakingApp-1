package com.example.android.bakingapp;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.android.bakingapp.model.Cake;

public class RecipeWidgetService extends IntentService {

    public static final String ACTION_RECIPE_UPDATE =
            "com.example.android.bakingapp.action.update";
    private static final String BUNDLE_RECIPE_WIDGET_DATA =
            "com.example.android.bakingapp.action.data";

    public RecipeWidgetService() {
        super("RecipeWidgetService");
    }


    public static void startActionRecipeUpdate(Context context, Cake cake){
        Intent intent = new Intent(context, RecipeWidgetService.class);
        intent.setAction(ACTION_RECIPE_UPDATE);
        intent.putExtra(BUNDLE_RECIPE_WIDGET_DATA, cake);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent != null){
            final String action = intent.getAction();
            if(ACTION_RECIPE_UPDATE.equals(action) &&
                    intent.getParcelableExtra(BUNDLE_RECIPE_WIDGET_DATA) != null){
                handleActionRecipeUpdate(intent.<Cake>getParcelableExtra(BUNDLE_RECIPE_WIDGET_DATA));
            }
        }
    }

    private void handleActionRecipeUpdate(Cake cake){
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecipeWidget.class));
        RecipeWidget.updateBakingWidgets(this, appWidgetManager, appWidgetIds, cake);
    }
}

