// 상위 폴더안에서 공용으로 사용할 컴포넌트
// 나중에 겹치는 곳이 많으면 글로벌로 빼도될듯

import React from 'react';
import {ColorValue, StyleProp, Text, TextStyle} from 'react-native';
import {COLORS} from '../../../../styles/colors';
import {pretendard} from '../../../../styles/textStyled';

interface FixedTextProps {
  color?: ColorValue; // 가장 마지막에 추가하여 오버라이딩 되도록
  numberOfLines?: number; // 라인 수를 고정하고 싶을때 사용, 기본값을 undefined 로
  style?: StyleProp<TextStyle>; // 마진 같은 거 주고 싶을때, 오버라이딩 순서 고려
}

export const BookTitle: React.FC<FixedTextProps> = ({children, color = COLORS.primaryWhite, numberOfLines, style}) => {
  return (
    <Text
      allowFontScaling={false}
      ellipsizeMode={'tail'}
      numberOfLines={numberOfLines}
      style={[pretendard.bold20, {color: color}, style]}>
      {children}
    </Text>
  );
};

export const BookContent: React.FC<FixedTextProps> = ({children, color = COLORS.grey1, numberOfLines, style}) => {
  return (
    <Text
      allowFontScaling={false}
      ellipsizeMode={'tail'}
      numberOfLines={numberOfLines}
      style={[pretendard.medium15, {color: color}, style]}>
      {children}
    </Text>
  );
};

export const BookInfoContent: React.FC<FixedTextProps> = ({children, color = COLORS.grey1, numberOfLines, style}) => {
  return (
    <Text
      allowFontScaling={false}
      ellipsizeMode={'tail'}
      numberOfLines={numberOfLines}
      style={[pretendard.medium13, {color: color}, style]}>
      {children}
    </Text>
  );
};

export const BookInfo: React.FC<FixedTextProps> = ({children, color = COLORS.primaryWhite, numberOfLines, style}) => {
  return (
    <Text
      allowFontScaling={false}
      ellipsizeMode={'tail'}
      numberOfLines={numberOfLines}
      style={[pretendard.bold15, {color: color}, style]}>
      {children}
    </Text>
  );
};
