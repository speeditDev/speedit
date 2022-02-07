import React from 'react';
import {ColorValue, Image, TouchableOpacity} from 'react-native';
import {COLORS} from '../styles/colors';

interface HeaderBackProps {
  navigate: () => void;
  color?: ColorValue;
}

const HeaderBack = ({navigate, color = COLORS.primaryWhite}: HeaderBackProps) => (
  <TouchableOpacity
    onPress={() => navigate()}
    style={{width: 30, height: 30, alignItems: 'center', justifyContent: 'center'}}>
    <Image source={require('../images/header_back.png')} width={24} height={24} />
  </TouchableOpacity>
);

export {HeaderBack};
