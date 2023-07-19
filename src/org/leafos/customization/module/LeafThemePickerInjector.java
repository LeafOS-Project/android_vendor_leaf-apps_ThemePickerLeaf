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
import com.android.wallpaper.module.CustomizationSections;

public class LeafThemePickerInjector extends ThemePickerInjector {
    private CustomizationSections mCustomizationSections;

    @Override
    public CustomizationSections getCustomizationSections(ComponentActivity activity) {
        if (mCustomizationSections == null) {
            mCustomizationSections = new LeafCustomizationSections(super.getCustomizationSections(activity));
        }
        return mCustomizationSections;
    }
}
