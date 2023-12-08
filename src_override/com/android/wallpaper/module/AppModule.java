/*
 * Copyright (C) 2023 The LeafOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.wallpaper.module;

import android.content.Context;
import com.android.customization.module.CustomizationInjector;
import com.android.customization.module.DefaultCustomizationPreferences;
import org.leafos.customization.module.LeafThemePickerInjector;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {
    @Binds @Singleton public abstract CustomizationInjector bindInjector(LeafThemePickerInjector impl);

    @Provides
    @Singleton
    public static WallpaperPreferences provideWallpaperPreferences(
        @ApplicationContext Context context
    ) {
        return new DefaultCustomizationPreferences(context);
    }
}
