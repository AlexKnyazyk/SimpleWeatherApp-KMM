package com.simple.weather.app.android.presentation.ui.utils

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween

const val ANIM_TRANSITION_DURATION = 200

@ExperimentalAnimationApi
fun <S> AnimatedContentScope<S>.slideOutLeft() = slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(ANIM_TRANSITION_DURATION))

@ExperimentalAnimationApi
fun <S> AnimatedContentScope<S>.slideInRight() = slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(ANIM_TRANSITION_DURATION))

@ExperimentalAnimationApi
fun <S> AnimatedContentScope<S>.slideOutRight() = slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(ANIM_TRANSITION_DURATION))

@ExperimentalAnimationApi
fun <S> AnimatedContentScope<S>.slideInLeft() = slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(ANIM_TRANSITION_DURATION))