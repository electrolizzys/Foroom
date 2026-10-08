package com.example.foroom.Helper

import android.view.View
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.util.TreeIterables
import com.example.design_system.components.image_chooser.ImageChooserItemView
import com.example.design_system.components.image_chooser.ImageChooserListView
import com.example.shared.model.Image
import org.hamcrest.Description
import org.hamcrest.Matcher

fun imagesLoaded(): Matcher<View> =
    object : BoundedMatcher<View, ImageChooserListView>(ImageChooserListView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("image chooser with loaded, selectable images")
        }

        override fun matchesSafely(item: ImageChooserListView): Boolean =
            item.isChoosingEnabled && item.images.isNotEmpty() &&
                item.images.none { image -> image.id == Image.BLANK_IMAGE_ID }
    }
fun imageSelectedAt(index: Int): Matcher<View> =
    object : BoundedMatcher<View, ImageChooserListView>(ImageChooserListView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("image chooser with selected index $index")
        }
        override fun matchesSafely(item: ImageChooserListView): Boolean =
            item.selectedIndex == index
    }

fun imageAt(chooserId: Int, index: Int): Matcher<View> =
    object : BoundedMatcher<View, ImageChooserItemView>(ImageChooserItemView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("image at index $index in chooser $chooserId")
        }

        override fun matchesSafely(item: ImageChooserItemView): Boolean {
            var parent = item.parent
            while (parent != null && parent !is ImageChooserListView) parent = parent.parent
            val chooser = parent as? ImageChooserListView ?: return false
            if (chooser.id != chooserId) return false

            val images = TreeIterables.breadthFirstViewTraversal(chooser)
                .filterIsInstance<ImageChooserItemView>()
            return images.getOrNull(index) === item
        }
    }
