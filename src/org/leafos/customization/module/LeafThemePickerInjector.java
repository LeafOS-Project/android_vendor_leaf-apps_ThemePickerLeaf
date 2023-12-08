/*
 * Copyright (c) 2023 The LeafOS Project
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
package org.leafos.customization.module;

import androidx.activity.ComponentActivity;

import com.android.customization.module.ThemePickerInjector;
import com.android.wallpaper.dispatchers.BackgroundDispatcher;
import com.android.wallpaper.dispatchers.MainDispatcher;
import com.android.wallpaper.module.CustomizationSections;

import javax.inject.Inject;
import javax.inject.Singleton;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

@Singleton
public class LeafThemePickerInjector extends ThemePickerInjector {
    private CustomizationSections mCustomizationSections;

    @Inject
    protected LeafThemePickerInjector(@MainDispatcher CoroutineScope mainScope,
            @MainDispatcher CoroutineDispatcher mainDispatcher,
            @BackgroundDispatcher CoroutineDispatcher bgDispatcher) {
        super(mainScope, mainDispatcher, bgDispatcher);
    }

    @Override
    public CustomizationSections getCustomizationSections(ComponentActivity activity) {
        if (mCustomizationSections == null) {
            mCustomizationSections = new LeafCustomizationSections(
                    super.getCustomizationSections(activity),
                    getKeyguardQuickAffordancePickerInteractor(activity),
                    getKeyguardQuickAffordancePickerViewModelFactory(activity));
        }
        return mCustomizationSections;
    }
}
