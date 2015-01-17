// ////////////////////////////////////////////////////////////////////////////
//
// Author: Volkmar Seifert
// Description:
// A simple DashboardItem implementation that can be used out of the box to
// fill DashboardLayout. It can also be extended for more complex DashboardItems
// while maintaining the benefits of this class's implementation.
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
 * A simple {@link DashboardItem} implementation that can be used out of the box to
 * fill a {@link DashboardLayout}. It can also be extended for more complex {@code DashboardItems}
 * while maintaining the benefits of this class's implementation.
 */
public class SimpleDashboardItem implements DashboardItem {
  protected long id;
  protected CharSequence text;
  protected boolean enabled = false;
  protected Drawable iconEnabled = null;
  protected Drawable iconDisabled = null;
  private DashboardItem.OnClickListener onClickListener = null;

  /**
   * <p>Creates a new {@code ContainerDashboardItem} from the given parameters.</p>
   * <p>This constructor creates the item as "enabled", which means it will be active and clickable.</p>
   *
   * @param id The ID of this item (required for later identification when an item gets clicked upon).
   * @param text The text the should show up under the icon in the dashboard.
   * @param iconEnabled The icon for the {@code enabled == true} state.
   * @param iconDisabled The icon for the {@code enabled == false} state.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public SimpleDashboardItem(long id, String text, Drawable iconEnabled, Drawable iconDisabled) {
    this(id, text, iconEnabled, iconDisabled, true);
  }

  /**
   * <p>Creates a new {@code ContainerDashboardItem} from the given parameters.</p>
   *
   * @param id The ID of this item (required for later identification when an item gets clicked upon).
   * @param text The text the should show up under the icon in the dashboard.
   * @param iconEnabled The icon for the {@code enabled == true} state.
   * @param iconDisabled The icon for the {@code enabled == false} state.
   * @param enabled The state that defines whether the item is active and clickable ({@code true}) or not ({@code false}).
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public SimpleDashboardItem(long id, String text, Drawable iconEnabled, Drawable iconDisabled, boolean enabled) {
    this.id = id;
    this.text = text;
    this.iconEnabled = iconEnabled;
    this.iconDisabled = iconDisabled;
    this.enabled = enabled;
  }

  @Override
  public Drawable getIconDrawable() {
    // always return the currently valid drawable...
    return ((iconDisabled != null) && (!isEnabled())) ? iconDisabled : iconEnabled;
  }

  /**
   * Sets the icon for the enabled state.
   *
   * @param icon The icon to be displayed when the {@link DashboardItem} is enabled.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  protected void setIconEnabled(Drawable icon) {
    iconEnabled = icon;
  }

  /**
   * Sets the icon for the disabled state.
   *
   * @param icon The icon to be displayed when the {@link DashboardItem} is disabled.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  protected void setIconDisabled(Drawable icon) {
    iconDisabled = icon;
  }

  @Override
  public CharSequence getText() {
    return text;
  }

  /**
   * Sets the text to be displayed below the icon.
   *
   * @param text The text.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  protected void setText(String text) {
    this.text = text;
  }

  @Override
  public long getID() {
    return id;
  }

  /**
   * Sets the ID of this {@link DashboardItem}.
   *
   * @param id The ID.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  protected void setID(long id) {
    this.id = id;
  }

  @Override
  public void setEnabled(boolean state) {
    enabled = state;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @Override
  public boolean hasOnClickListener() {
    return onClickListener != null;
  }

  @Override
  public void setOnClickListener(OnClickListener listener) {
    onClickListener = listener;
  }

  @Override
  public void onClick(View v) {
    if(onClickListener != null) {
      onClickListener.onClick(this);
    }
  }
}
