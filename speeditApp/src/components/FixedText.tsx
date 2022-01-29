import React from 'react';
import {ColorValue, Text} from 'react-native';
import {textColor} from '../styles/colors';
import {pretendard} from '../styles/textStyled';

interface FixedTextProps {
  color?: ColorValue; // 가장 마지막에 추가하여 오버라이딩 되도록
  numberOfLines?: number; // 라인 수를 고정하고 싶을때 사용, 기본값을 undefined 로
}

// color 기본값을 정해놓고, 값이 있을때 덮어씌우도록 설정
export const ContentKr: React.FC<FixedTextProps> = ({children, color = textColor['100'], numberOfLines}) => {
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

export const AlertMessageKr: React.FC<FixedTextProps> = ({children, color = textColor['100'], numberOfLines}) => {
  return (
    <Text
      allowFontScaling={false}
      ellipsizeMode={'tail'}
      numberOfLines={numberOfLines}
      style={[pretendard.bold13, {color: color}]}>
      {children}
    </Text>
  );
};
