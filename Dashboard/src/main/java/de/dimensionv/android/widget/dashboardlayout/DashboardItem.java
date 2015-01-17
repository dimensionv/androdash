// ////////////////////////////////////////////////////////////////////////////
//
// Author: Volkmar Seifert
// Description:
// Interface for a dashboard item. The DashboardLayout assumes all items
// actually implement this interface.
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
import android.view.View;

/**
 * This interface is the base for all items displayed by the DashboardLayout. All operations with
 * "items" in the Dashboard assume the items to have implemented this interface.
 * <p/>
 * An "item" is an object consisting of an icon and a text, which is displayed on the screen, plus
 * an ID for internal identification and usage.
 *
 * @author Volkmar Seifert
 * @version 1.0
 * @since API 1.0.0
 */
public interface DashboardItem extends View.OnClickListener {

  /**
   * <p>Public internal interface for onClick-events.</p>
   * This interface is used to transform a {@link View.OnClickListener} event into a
   * {@code DashboardItem.OnClickListener} event.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public interface OnClickListener {
    public void onClick(DashboardItem item);
  }

  /**
   * Returns the drawable object of the item. This can be either an icon or some other kind of
   * graphical representation. If necessary, this method has to check the enabled-state in order to
   * return the appropriate drawable object. In most cases, this can probably be omitted, because
   * the underlying button-object that is the actual dashboard-element on the GUI will take care of
   * a disabled looks.
   *
   * @return The item's drawable object
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public Drawable getIconDrawable();

  /**
   * Returns the text to be displayed under the drawable object returned by the
   * {@code getIconDrawable()} method. (Think of it as a name/label)
   *
   * @return The item's label
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public CharSequence getText();

  /**
   * Returns the ID associated with the item. It's the items unique identifier, and you are free to
   * use any kind of number best suited to your case.
   * <p/>
   * Please note: this ID will be used as the actual button's ID by a cast to {@code int}.
   *
   * @return the item' ID.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public long getID();

  /**
   * Sets the state of the DashboardItem. The state can be either {@code true}, which means the
   * item is enabled (active and clickable), or {@code false}, which means the item is disabled
   * (neither active nor clickable).
   *
   * @param state
   *     {@code true} for enabled, {@code false} for disabled state.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public void setEnabled(boolean state);

  /**
   * Returns the current state of the DashboardItem. {@code true} for an enabled state,
   * {@code false} otherwise.
   *
   * @return {@code true} for enabled, {@code false} for disabled state.
   *
   * @see DashboardItem#setEnabled
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public boolean isEnabled();

  /**
   * Checks whether this {@code DashboardItem} has a {@link DashboardItem.OnClickListener} set or not.
   *
   * @return True if a {@code DashboardItem.OnClickListener} is set, false otherwise.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public boolean hasOnClickListener();

  /**
   * Sets a {@link DashboardItem.OnClickListener} for this {@code DashboardItem}.
   *
   * @param listener The {@code DashboardItem.OnClickListener} for this {@code DashboardItem}.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public void setOnClickListener(OnClickListener listener);
}
