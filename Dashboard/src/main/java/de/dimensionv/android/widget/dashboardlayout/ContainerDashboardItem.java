// ////////////////////////////////////////////////////////////////////////////
//
// Author: Volkmar Seifert
// Description:
// DashboardItem implementation extending the SimpleDashboardItem by the
// capability to act as a container for the actual object (the content-object)
// that should appear in the dashboard. If this content-object also implements
// the DashboardItemContent interface, the data required for representing a
// true DashboardItem, like ID, displayed text and the icons, is extracted from
// the content-object.
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
 * <p>{@link DashboardItem} implementation extending the {@link SimpleDashboardItem} by the
 * capability to act as a container for the actual object (the content-object)
 * that should appear in the {@link DashboardLayout}.</p>
 * <p>If this content-object also implements
 * the {@link DashboardItemContent} interface, the data required for representing a
 * true {@link DashboardItem}, like ID, displayed text and the icons, is extracted from
 * the content-object.</p>
 *
 * @author Volkmar Seifert
 * @version 1.0
 * @since API 1.0.0
 */
public class ContainerDashboardItem<T> extends SimpleDashboardItem {

  /**
   * The content-object
   */
  private T content = null;

  /**
   * <p>Creates a new {@code ContainerDashboardItem} from the given {@code content}-object, and sets
   * it into the state given by the parameter {@code enabled}.</p>
   * <p>For this constructor to work properly, {@code context} needs to implement the
   * {@link DashboardItemContent}-interface. If it does not, this constructor will throw a
   * {@link ClassCastException}.</p>
   *
   * @param content The content-object to be used and added. Must implement the {@code DashboardItemContent}-interface.
   * @param enabled The state that defines whether the item is active and clickable ({@code true}) or not ({@code false}).
   *
   * @throws ClassCastException A {@code ClassCastException} will be thrown in case the
   *                            content-object does not implement the {@code DashboardItemContent} interface.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public ContainerDashboardItem(T content, boolean enabled) {
    this(((DashboardItemContent) content).getID(), ((DashboardItemContent) content).getText(), ((DashboardItemContent) content).getIconDrawableEnabled(), ((DashboardItemContent) content).getIconDrawableDisabled(), enabled);
  }

  /**
   * <p>Creates a new {@code ContainerDashboardItem} from the given parameters.</p>
   * <p>This is one of the constructors that should be used in case the content-object does not
   * implement the {@link DashboardItemContent} interface.</p>
   * <p>This constructor does not set the content-object</p>
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
  public ContainerDashboardItem(long id, String text, Drawable iconEnabled, Drawable iconDisabled) {
    this(id, text, iconEnabled, iconDisabled, true);
  }

  /**
   * <p>Creates a new {@code ContainerDashboardItem} from the given parameters.</p>
   * <p>This is one of the constructors that should be used in case the content-object does not
   * implement the {@link DashboardItemContent} interface.</p>
   * <p>This constructor does not set the content-object</p>
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
  public ContainerDashboardItem(long id, String text, Drawable iconEnabled, Drawable iconDisabled, boolean enabled) {
    super(id, text, iconEnabled, iconDisabled, enabled);
  }

  /**
   * <p>Creates a new {@code ContainerDashboardItem} from the given parameters.</p>
   * <p>This is one of the constructors that should be used in case the content-object does not
   * implement the {@link DashboardItemContent} interface.</p>
   * <p>This constructor creates the item as "enabled", which means it will be active and clickable.</p>
   *
   * @param id The ID of this item (required for later identification when an item gets clicked upon).
   * @param text The text the should show up under the icon in the dashboard.
   * @param iconEnabled The icon for the {@code enabled == true} state.
   * @param iconDisabled The icon for the {@code enabled == false} state.
   * @param content The content-object
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public ContainerDashboardItem(long id, String text, Drawable iconEnabled, Drawable iconDisabled, T content) {
    this(id, text, iconEnabled, iconDisabled, content, true);
  }

  /**
   * <p>Creates a new {@code ContainerDashboardItem} from the given parameters.</p>
   * <p>This is one of the constructors that should be used in case the content-object does not
   * implement the {@link DashboardItemContent} interface.</p>
   *
   * @param id The ID of this item (required for later identification when an item gets clicked upon).
   * @param text The text the should show up under the icon in the dashboard.
   * @param iconEnabled The icon for the {@code enabled == true} state.
   * @param iconDisabled The icon for the {@code enabled == false} state.
   * @param content The content-object
   * @param enabled The state that defines whether the item is active and clickable ({@code true}) or not ({@code false}).
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public ContainerDashboardItem(long id, String text, Drawable iconEnabled, Drawable iconDisabled, T content, boolean enabled) {
    this(id, text, iconEnabled, iconDisabled, enabled);
    this.content = content;
  }

  /**
   * Returns the content object.
   *
   * @return The content object of this {@code ContainerDashboardItem} object.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public T getContent() {
    return content;
  }

  /**
   * Sets the content-object for this {@code ContainerDashboardItem} object.
   *
   * @param content The content object for this {@code ContainerDashboardItem} object.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public void setContent(T content) {
    this.content = content;
    if(content instanceof DashboardItemContent) {
      DashboardItemContent dic = (DashboardItemContent) content;
      setID(dic.getID());
      setText(dic.getText());
      setIconEnabled(dic.getIconDrawableEnabled());
      setIconDisabled(dic.getIconDrawableDisabled());
    }
  }
}
