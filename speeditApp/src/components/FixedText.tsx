import React from 'react';
import {ColorValue, StyleProp, Text, TextStyle} from 'react-native';
import {COLORS} from '../styles/colors';
import {pretendard} from '../styles/textStyled';

interface FixedTextProps {
  color?: ColorValue; // 가장 마지막에 추가하여 오버라이딩 되도록
  numberOfLines?: number; // 라인 수를 고정하고 싶을때 사용, 기본값을 undefined 로
  style?: StyleProp<TextStyle>; // 마진 같은 거 주고 싶을때, 오버라이딩 순서 고려
}

interface LengthProps {
  lengthSize?: 'S' | 'M' | 'L'; // 글자수에 따라 사이즈 바꿔주기 위해 필요함, 필요한 곳만 사용, 글자가 적은게 S ( 이때 제일 글자가 큼 )
}

export const Category: React.FC<FixedTextProps> = ({children, color = COLORS.primaryWhite, numberOfLines, style}) => {
  return (
    <Text
      allowFontScaling={false}
      ellipsizeMode={'tail'}
      numberOfLines={numberOfLines}
      style={[pretendard.bold16, {color: color}, style]}>
      {children}
    </Text>
  );
};

export const Sentence: React.FC<FixedTextProps & LengthProps> = ({
  children,
  color = COLORS.whiteSentence,
  numberOfLines,
  style,
  lengthSize = 'S',
}) => {
  return (
    <Text
      allowFontScaling={false}
      ellipsizeMode={'tail'}
      numberOfLines={numberOfLines}
      style={[
        lengthSize === 'S' ? pretendard.bold22 : lengthSize === 'M' ? pretendard.bold20 : pretendard.bold18,
        {color: color},
        style,
      ]}>
      {children}
    </Text>
  );
};

export const Comment: React.FC<FixedTextProps> = ({children, color = COLORS.whiteSentence, numberOfLines, style}) => {
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

export const Like: React.FC<FixedTextProps> = ({children, color = COLORS.grey1, numberOfLines, style}) => {
  return (
    <Text
      allowFontScaling={false}
      ellipsizeMode={'tail'}
      numberOfLines={numberOfLines}
      style={[pretendard.bold12, {color: color}, style]}>
      {children}
    </Text>
  );
};

export const MyName: React.FC<FixedTextProps> = ({children, color = COLORS.primaryWhite, numberOfLines, style}) => {
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

export const MyCompany: React.FC<FixedTextProps> = ({children, color = COLORS.grey3, numberOfLines, style}) => {
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

// color 기본값을 정해놓고, 값이 있을때 덮어씌우도록 설정
// todo : sample
export const ContentKr: React.FC<FixedTextProps> = ({children, color = COLORS.primaryWhite, numberOfLines}) => {
  return (
    <Text
      allowFontScaling={false}
      ellipsizeMode={'tail'}
      numberOfLines={numberOfLines}
      style={[pretendard.extraBold32, {color: color}]}>
      {children}
    </Text>
  );
};

// todo : sample
export const AlertMessageKr: React.FC<FixedTextProps> = ({children, color = COLORS.primaryWhite, numberOfLines}) => {
  return (
    <Text
      allowFontScaling={false}
      ellipsizeMode={'tail'}
      numberOfLines={numberOfLines}
      style={[pretendard.bold16, {color: color}]}>
      {children}
    </Text>
  );
};
