# ArcStatusBar
<p align="center">
<img src="./screenshots/ss1.jpg" alt="Screenshot" height="140"/>
</p>
<p>A simple and lightweight whatsapp-like-status-bar that fully customizable.</p>
<p><b>ArcStatusBar</b> is a custom Android View that visually represents a user's status (e.g., stories) using customizable arc-based bars, similar to the status bars in WhatsApp or Instagram. It supports features such as viewing/unviewing bars, dynamic updates, and XML or programmatic integration.</p>

## Features
- **Customizable Bars:** Configure bar width, colors, and gap sizes.
- **Dynamic Updates:** Add, remove, view, or unview bars programmatically.
- **XML and Programmatic Usage:** Can be used directly in XML layouts or instantiated programmatically.
- **Circular Design:** Bars are rendered in a circular pattern with a transparent center.

## Preview
<p align="center">
<img src="./screenshots/ss2.jpg" alt="Screenshot"/>
<img src="./screenshots/ss3.jpg" alt="Screenshot"/>
</p>

## Installation
1. **Add the class to your project:**
   Copy the `ArcStatusBar` class into your project.

2. **Add to your layout XML:**
   ```xml
   <com.yourpackage.ArcStatusBar
       android:id="@+id/arc_status_bar"
       android:layout_width="100dp"
       android:layout_height="100dp"
       android:background="@android:color/transparent" />
   ```

3. **Instantiate Programmatically:**
   ```java
   ArcStatusBar arcStatusBar = new ArcStatusBar(context);
   arcStatusBar.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
   arcStatusBar.setData(yourArcDataList);
   ```
