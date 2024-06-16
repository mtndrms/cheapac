package com.example.cheapac.presentation.component.top_bar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cheapac.presentation.common.CheapacIcons


@Composable
@Preview(group = "Center")
private fun PreviewHeaderTitleCenterNoButtons() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.CENTER,
    )
}

@Composable
@Preview(group = "Center")
private fun PreviewHeaderTitleCenterOnlyNavigationButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.CENTER,
        navigationButtonOpts = TopBarButtonOpts(icon = CheapacIcons.ArrowBack),
    )
}

@Composable
@Preview(group = "Center")
private fun PreviewHeaderTitleCenterOnlyPrimaryButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.CENTER,
        primaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.Clear),
    )
}

@Composable
@Preview(group = "Center")
private fun PreviewHeaderTitleCenterOnlySecondaryButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.CENTER,
        secondaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.DeleteOutlined),
    )
}

@Composable
@Preview(group = "Center")
private fun PreviewHeaderTitleCenterPrimarySecondaryButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.CENTER,
        primaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.Clear),
        secondaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.DeleteOutlined),
    )
}

@Composable
@Preview(group = "Center")
private fun PreviewHeaderTitleCenterNavAndPrimaryButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.CENTER,
        navigationButtonOpts = TopBarButtonOpts(icon = CheapacIcons.ArrowBack),
        primaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.Clear),
    )
}

@Composable
@Preview(group = "Center")
private fun PreviewHeaderTitleCenterNavAndSecondaryButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.CENTER,
        navigationButtonOpts = TopBarButtonOpts(icon = CheapacIcons.ArrowBack),
        secondaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.DeleteOutlined),
    )
}

@Composable
@Preview(group = "Center")
private fun PreviewHeaderTitleCenterFullButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.CENTER,
        navigationButtonOpts = TopBarButtonOpts(icon = CheapacIcons.ArrowBack),
        primaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.Clear),
        secondaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.DeleteOutlined),
    )
}

@Composable
@Preview(group = "Center")
private fun PreviewHeaderTitleCenterHideSecondary() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.CENTER,
        navigationButtonOpts = TopBarButtonOpts(icon = CheapacIcons.ArrowBack),
        primaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.Clear),
        secondaryButtonOpts = TopBarButtonOpts(
            icon = CheapacIcons.DeleteOutlined,
            shouldHideButton = true
        ),
    )
}

@Composable
@Preview(group = "Center")
private fun PreviewHeaderTitleCenterHidePrimary() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.CENTER,
        navigationButtonOpts = TopBarButtonOpts(icon = CheapacIcons.ArrowBack),
        primaryButtonOpts = TopBarButtonOpts(
            icon = CheapacIcons.Clear,
            shouldHideButton = true
        ),
        secondaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.DeleteOutlined),
    )
}

@Composable
@Preview(group = "Start")
private fun PreviewHeaderTitleStartNoButtons() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.START,
    )
}

@Composable
@Preview(group = "Start")
private fun PreviewHeaderTitleStartOnlyNavigationButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.START,
        navigationButtonOpts = TopBarButtonOpts(icon = CheapacIcons.ArrowBack),
    )
}

@Composable
@Preview(group = "Start")
private fun PreviewHeaderTitleStartOnlyPrimaryButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.START,
        primaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.Clear),
    )
}

@Composable
@Preview(group = "Start")
private fun PreviewHeaderTitleStartOnlySecondaryButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.START,
        secondaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.DeleteOutlined),
    )
}

@Composable
@Preview(group = "Start")
private fun PreviewHeaderTitleStartPrimarySecondaryButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.START,
        primaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.Clear),
        secondaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.DeleteOutlined),
    )
}

@Composable
@Preview(group = "Start")
private fun PreviewHeaderTitleStartNavAndPrimaryButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.START,
        navigationButtonOpts = TopBarButtonOpts(icon = CheapacIcons.ArrowBack),
        primaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.Clear),
    )
}

@Composable
@Preview(group = "Start")
private fun PreviewHeaderTitleStartNavAndSecondaryButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.START,
        navigationButtonOpts = TopBarButtonOpts(icon = CheapacIcons.ArrowBack),
        secondaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.DeleteOutlined),
    )
}

@Composable
@Preview(group = "Start")
private fun PreviewHeaderTitleStartFullButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.START,
        navigationButtonOpts = TopBarButtonOpts(icon = CheapacIcons.ArrowBack),
        primaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.Clear),
        secondaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.DeleteOutlined),
    )
}

@Composable
@Preview(group = "Start")
private fun PreviewHeaderTitleStartHideSecondary() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.START,
        navigationButtonOpts = TopBarButtonOpts(icon = CheapacIcons.ArrowBack),
        primaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.Clear),
        secondaryButtonOpts = TopBarButtonOpts(
            icon = CheapacIcons.DeleteOutlined,
            shouldHideButton = true
        ),
    )
}

@Composable
@Preview(group = "Start")
private fun PreviewHeaderTitleStartHidePrimary() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.START,
        navigationButtonOpts = TopBarButtonOpts(icon = CheapacIcons.ArrowBack),
        primaryButtonOpts = TopBarButtonOpts(
            icon = CheapacIcons.Clear,
            shouldHideButton = true
        ),
        secondaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.DeleteOutlined),
    )
}

@Composable
@Preview(group = "End")
private fun PreviewHeaderTitleEndNoButtons() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.END,
    )
}

@Composable
@Preview(group = "End")
private fun PreviewHeaderTitleEndOnlyNavigationButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.END,
        navigationButtonOpts = TopBarButtonOpts(icon = CheapacIcons.ArrowBack),
    )
}

@Composable
@Preview(group = "End")
private fun PreviewHeaderTitleEndOnlyPrimaryButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.END,
        primaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.Clear),
    )
}

@Composable
@Preview(group = "End")
private fun PreviewHeaderTitleEndOnlySecondaryButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.END,
        secondaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.DeleteOutlined),
    )
}

@Composable
@Preview(group = "End")
private fun PreviewHeaderTitleEndPrimarySecondaryButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.END,
        primaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.Clear),
        secondaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.DeleteOutlined),
    )
}

@Composable
@Preview(group = "End")
private fun PreviewHeaderTitleEndNavAndPrimaryButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.END,
        navigationButtonOpts = TopBarButtonOpts(icon = CheapacIcons.ArrowBack),
        primaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.Clear),
    )
}

@Composable
@Preview(group = "End")
private fun PreviewHeaderTitleEndNavAndSecondaryButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.END,
        navigationButtonOpts = TopBarButtonOpts(icon = CheapacIcons.ArrowBack),
        secondaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.DeleteOutlined),
    )
}

@Composable
@Preview(group = "End")
private fun PreviewHeaderTitleEndFullButton() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.END,
        navigationButtonOpts = TopBarButtonOpts(icon = CheapacIcons.ArrowBack),
        primaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.Clear),
        secondaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.DeleteOutlined),
    )
}

@Composable
@Preview(group = "End")
private fun PreviewHeaderTitleEndHideSecondary() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.END,
        navigationButtonOpts = TopBarButtonOpts(icon = CheapacIcons.ArrowBack),
        primaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.Clear),
        secondaryButtonOpts = TopBarButtonOpts(
            icon = CheapacIcons.DeleteOutlined,
            shouldHideButton = true
        ),
    )
}

@Composable
@Preview(group = "End")
private fun PreviewHeaderTitleEndHidePrimary() {
    TopBar(
        title = "Cheapac",
        titleLocation = TopBarTitleLocation.END,
        navigationButtonOpts = TopBarButtonOpts(icon = CheapacIcons.ArrowBack),
        primaryButtonOpts = TopBarButtonOpts(
            icon = CheapacIcons.Clear,
            shouldHideButton = true
        ),
        secondaryButtonOpts = TopBarButtonOpts(icon = CheapacIcons.DeleteOutlined),
    )
}
