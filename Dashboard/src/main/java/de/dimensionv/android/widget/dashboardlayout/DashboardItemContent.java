// ////////////////////////////////////////////////////////////////////////////
//
// Author: Volkmar Seifert
// Description:
// Interface for a dashboard content item.
//
// ////////////////////////////////////////////////////////////////////////////
// License:
// // // // // // // // // // // // // // // // // // // //
// Copyright 2011-2014 Volkmar Seifert <vs@dimensionv.de>.
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
// are met:
// 1. Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
// 2. Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY VOLKMAR SEIFERT AND CONTRIBUTORS ``AS IS''
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED. IN NO EVENT SHALL THE FOUNDATION OR CONTRIBUTORS BE LIABLE
// FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
// DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
// SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
// CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
// LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
// OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
// DAMAGE.
//
// The views and conclusions contained in the software and documentation are
// those of the authors and should not be interpreted as representing official
// policies, either expressed or implied, of Volkmar Seifert <vs@dimensionv.de>.
// ////////////////////////////////////////////////////////////////////////////
package de.dimensionv.android.widget.dashboardlayout;

import android.graphics.drawable.Drawable;

/**
 * <p>This interface should be used for all content-objects passed to the {@link ContainerDashboardItem}.</p>
 * <p>If a content-object implements this interface, it can be used to automatically feed the information
 * necessary to create a {@link DashboardItem}.</p>
 *
 * @author Volkmar Seifert
 * @version 1.0
 * @since API 1.0.0
 */
public interface DashboardItemContent {
  /**
   * Returns the ID for the {@link DashboardItem}.
   * @return The ID for the {@link DashboardItem}.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  long getID();
  /**
   * Returns the text to be displayed in the {@link DashboardLayout}.
   * @return The text to be displayed in the {@link DashboardLayout}.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  String getText();
  /**
   * Returns the {@link Drawable}-object of the icon for the {@code enabled == true} state.
   * @return The {@code Drawable}-object of the icon.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  Drawable getIconDrawableEnabled();
  /**
   * Returns the {@link Drawable}-object of the icon for the {@code enabled == false} state.
   * @return The {@code Drawable}-object of the icon.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  Drawable getIconDrawableDisabled();
}
