import React from 'react';
import {StyleSheet, Text, TouchableOpacity} from 'react-native';
import {COLORS} from '../styles/colors';
import {pretendard} from '../styles/textStyled';
import {spacing} from '../styles/spacing';

const styles = StyleSheet.create({
  // 기본버튼 ( 테투리 없는거 )
  primaryNormal: {
    height: 56,
    backgroundColor: COLORS.primaryWhite,
    borderRadius: 4,
    alignItems: 'center',
    justifyContent: 'center',
    paddingHorizontal: spacing.s,
  },
  primarySelected: {
    height: 56,
    backgroundColor: COLORS.grey7,
    borderRadius: 4,
    alignItems: 'center',
    justifyContent: 'center',
    paddingHorizontal: spacing.s,
  },
  // 라지 버튼
  largeNormal: {
    height: 56,
    borderRadius: 4,
    borderWidth: 1,
    borderColor: COLORS.grey4,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'transparent',
    paddingHorizontal: spacing.s,
  },
  largeSelected: {
    height: 56,
    borderRadius: 4,
    borderWidth: 1.5,
    borderColor: COLORS.primaryWhite,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'transparent',
    paddingHorizontal: spacing.s,
  },

  // 미디엄 버튼
  mediumNormal: {
    height: 44,
    borderRadius: 4,
    borderWidth: 1,
    borderColor: COLORS.grey4,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'transparent',
    paddingHorizontal: spacing.s,
  },
  mediumSelected: {
    height: 44,
    borderRadius: 4,
    borderWidth: 1.5,
    borderColor: COLORS.primaryWhite,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'transparent',
    paddingHorizontal: spacing.s,
  },
});

const textStyles = StyleSheet.create({
  primaryNormal: {...pretendard.bold16, color: COLORS.black},
  primarySelected: {...pretendard.bold16, color: COLORS.grey3},
  largeNormal: {...pretendard.bold18, color: COLORS.grey2},
  largeSelected: {...pretendard.bold18, color: COLORS.primaryWhite},
  mediumNormal: {...pretendard.bold18, color: COLORS.grey2},
  mediumSelected: {...pretendard.bold18, color: COLORS.primaryWhite},
});

interface ButtonProps {
  selected?: boolean;
  onPress: () => void;
  buttonTitle?: string;
}

const Button = ({selected = false, onPress, buttonTitle = '확인'}: ButtonProps) => (
  <TouchableOpacity onPress={onPress} style={selected ? styles.primarySelected : styles.primaryNormal}>
    <Text style={selected ? textStyles.primarySelected : textStyles.primaryNormal}>{buttonTitle}</Text>
  </TouchableOpacity>
);
const LargeButton = ({selected = false, onPress, buttonTitle = '확인'}: ButtonProps) => (
  <TouchableOpacity onPress={onPress} style={selected ? styles.largeSelected : styles.largeNormal}>
    <Text style={selected ? textStyles.largeSelected : textStyles.largeNormal}>{buttonTitle}</Text>
  </TouchableOpacity>
);
const MediumButton = ({selected = false, onPress, buttonTitle = '확인'}: ButtonProps) => (
  <TouchableOpacity onPress={onPress} style={selected ? styles.mediumSelected : styles.mediumNormal}>
    <Text style={selected ? textStyles.mediumSelected : textStyles.mediumNormal}>{buttonTitle}</Text>
  </TouchableOpacity>
);

//  가져다 쓰는 곳에서 별도의 변수가 아닌 하나의 변수로 속성을 다르게 쓰는 것 처럼 방법
Button.Large = LargeButton;
Button.Medium = MediumButton;

export {Button};
