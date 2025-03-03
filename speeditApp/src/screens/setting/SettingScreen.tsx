import * as React from 'react';
import styled from 'styled-components/native';
import {AlertMessageKr, ContentKr} from '../../components/FixedText';
import {COLORS} from '../../styles/colors';

const View = styled.View`
  flex: 1;
  align-items: center;
  justify-content: center;
`;

const TEMP_STRING = 'abcdefghijklmABCDEFGHIJKLM123456가나다라마바사아자차카타파하';

const SettingScreen = () => {
  return (
    <View style={{flex: 1, backgroundColor: 'black'}}>
      <ContentKr>{TEMP_STRING}</ContentKr>
      <AlertMessageKr color={COLORS.primaryGreen}>{`사용가능한 닉네임 입니다.`}</AlertMessageKr>
    </View>
  );
};

export default SettingScreen;
