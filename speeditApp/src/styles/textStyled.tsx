import React from 'react';
import {StyleProp, StyleSheet, Text, TextStyle, View} from 'react-native';
import {textColor} from './colors';

// includeFontPadding 을 false 로 하는 이유 : 안드로이드에서 달라지는 부분을 고려하기위함

const LINE_HEIGHT_RATIO = 1.5;

export const outfit = StyleSheet.create({
  extraBold32: {
    includeFontPadding: false,
    fontFamily: 'Outfit-ExtraBold',
    fontSize: 32,
    lineHeight: 32 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
  },
  extraBold28: {
    includeFontPadding: false,
    fontFamily: 'Outfit-ExtraBold',
    fontSize: 28,
    lineHeight: 28 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
  },
  extraBold24: {
    includeFontPadding: false,
    fontFamily: 'Outfit-ExtraBold',
    fontSize: 24,
    lineHeight: 24 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
  },
  extraBold22: {
    includeFontPadding: false,
    fontFamily: 'Outfit-ExtraBold',
    fontSize: 22,
    lineHeight: 22 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
  },
  bold20: {
    includeFontPadding: false,
    fontFamily: 'Outfit-Bold',
    fontSize: 20,
    lineHeight: 20 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
  },
  bold18: {
    includeFontPadding: false,
    fontFamily: 'Outfit-Bold',
    fontSize: 18,
    lineHeight: 18 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
  },
  medium15: {
    includeFontPadding: false,
    fontFamily: 'Outfit-Medium',
    fontSize: 15,
    lineHeight: 15 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
  },
  medium14: {
    includeFontPadding: false,
    fontFamily: 'Outfit-Medium',
    fontSize: 14,
    lineHeight: 14 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
  },
  medium13: {
    includeFontPadding: false,
    fontFamily: 'Outfit-Medium',
    fontSize: 13,
    lineHeight: 13 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
  },
  medium12: {
    includeFontPadding: false,
    fontFamily: 'Outfit-Medium',
    fontSize: 12,
    lineHeight: 12 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
  },
});

export const pretendard = StyleSheet.create({
  extraBold32: {
    includeFontPadding: false,
    fontSize: 32,
    lineHeight: 32 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
    fontFamily: 'Pretendard-ExtraBold',
  },
  extraBold28: {
    includeFontPadding: false,
    fontSize: 28,
    lineHeight: 28 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
    fontFamily: 'Pretendard-ExtraBold',
  },
  extraBold24: {
    includeFontPadding: false,
    fontSize: 24,
    lineHeight: 24 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
    fontFamily: 'Pretendard-ExtraBold',
  },
  extraBold22: {
    includeFontPadding: false,
    fontSize: 22,
    lineHeight: 22 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
    fontFamily: 'Pretendard-ExtraBold',
  },
  bold20: {
    includeFontPadding: false,
    fontSize: 20,
    lineHeight: 20 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
    fontFamily: 'Pretendard-Bold',
  },
  bold18: {
    includeFontPadding: false,
    fontSize: 18,
    lineHeight: 18 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
    fontFamily: 'Pretendard-Bold',
  },
  bold15: {
    includeFontPadding: false,
    fontSize: 15,
    lineHeight: 15 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
    fontFamily: 'Pretendard-Medium',
  },
  bold14: {
    includeFontPadding: false,
    fontSize: 14,
    lineHeight: 14 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
    fontFamily: 'Pretendard-Bold',
  },
  bold13: {
    includeFontPadding: false,
    fontSize: 13,
    lineHeight: 13 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
    fontFamily: 'Pretendard-Bold',
  },
  bold12: {
    includeFontPadding: false,
    fontSize: 12,
    lineHeight: 12 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
    fontFamily: 'Pretendard-Bold',
  },
  medium15: {
    includeFontPadding: false,
    fontSize: 15,
    lineHeight: 15 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
    fontFamily: 'Pretendard-Medium',
  },
  medium14: {
    includeFontPadding: false,
    fontSize: 14,
    lineHeight: 14 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
    fontFamily: 'Pretendard-Medium',
  },
  medium13: {
    includeFontPadding: false,
    fontSize: 13,
    lineHeight: 13 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
    fontFamily: 'Pretendard-Medium',
  },
  medium12: {
    includeFontPadding: false,
    fontSize: 12,
    lineHeight: 12 * LINE_HEIGHT_RATIO,
    letterSpacing: -0.2,
    fontFamily: 'Pretendard-Medium',
  },
});
