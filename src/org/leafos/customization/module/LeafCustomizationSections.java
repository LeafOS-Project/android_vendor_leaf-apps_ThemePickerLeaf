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

import android.app.WallpaperManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import org.leafos.customization.model.font.FontManager;
import org.leafos.customization.model.font.FontSectionController;
import org.leafos.customization.model.iconpack.IconPackManager;
import org.leafos.customization.model.iconpack.IconPackSectionController;
import org.leafos.customization.model.iconshape.IconShapeManager;
import org.leafos.customization.model.iconshape.IconShapeSectionController;

import com.android.customization.model.theme.OverlayManagerCompat;
import com.android.wallpaper.model.CustomizationSectionController;
import com.android.wallpaper.model.CustomizationSectionController.CustomizationSectionNavigationController;
import com.android.wallpaper.model.PermissionRequester;
import com.android.wallpaper.model.WallpaperColorsViewModel;
import com.android.wallpaper.model.WallpaperPreviewNavigator;
import com.android.wallpaper.module.CurrentWallpaperInfoFactory;
import com.android.wallpaper.module.CustomizationSections;
import com.android.wallpaper.picker.customization.domain.interactor.WallpaperInteractor;
import com.android.wallpaper.picker.customization.ui.viewmodel.CustomizationPickerViewModel;
import com.android.wallpaper.util.DisplayUtils;

import java.util.List;

public class LeafCustomizationSections implements CustomizationSections {
    private CustomizationSections mDefaultCustomizationSections;

    public LeafCustomizationSections(CustomizationSections defaultCustomizationSections) {
        mDefaultCustomizationSections = defaultCustomizationSections;
    }

    @Override
    public List<CustomizationSectionController<?>> getSectionControllersForScreen(
            Screen screen,
            FragmentActivity activity,
            LifecycleOwner lifecycleOwner,
            WallpaperColorsViewModel wallpaperColorsViewModel,
            PermissionRequester permissionRequester,
            WallpaperPreviewNavigator wallpaperPreviewNavigator,
            CustomizationSectionNavigationController sectionNavigationController,
            @Nullable Bundle savedInstanceState,
            CurrentWallpaperInfoFactory wallpaperInfoFactory,
            DisplayUtils displayUtils,
            CustomizationPickerViewModel customizationPickerViewModel,
            WallpaperInteractor wallpaperInteractor,
            WallpaperManager wallpaperManager,
            boolean isTwoPaneAndSmallWidth) {
        List<CustomizationSectionController<?>> sections = mDefaultCustomizationSections
                .getSectionControllersForScreen(screen, activity, lifecycleOwner,
                        wallpaperColorsViewModel, permissionRequester, wallpaperPreviewNavigator,
                        sectionNavigationController, savedInstanceState, wallpaperInfoFactory,
                        displayUtils, customizationPickerViewModel,
                        wallpaperInteractor, wallpaperManager, isTwoPaneAndSmallWidth);

        // Icon pack selection section.
        sections.add(new IconPackSectionController(
                IconPackManager.getInstance(activity, new OverlayManagerCompat(activity)), sectionNavigationController));

        // Font selection section.
        sections.add(new FontSectionController(
                FontManager.getInstance(activity, new OverlayManagerCompat(activity)), sectionNavigationController));

        // Icon shape selection section.
        sections.add(new IconShapeSectionController(
                IconShapeManager.getInstance(activity, new OverlayManagerCompat(activity)), sectionNavigationController));

        return sections;
    }

}
