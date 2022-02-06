import * as React from 'react';
import {useEffect, useState} from 'react';
import styled from 'styled-components/native';
import {SearchTabStackNavigationProps} from '../../../navigation/SearchTabStackNavigator';
import {TextInput} from 'react-native';

const View = styled.View`
  flex: 1;
  align-items: center;
  justify-content: center;
`;

const Text = styled.Text`
  font-size: 16px;
  color: ${props => props.theme.textColor.main};
`;

const Button = styled.TouchableOpacity`
  width: 50%;
  padding-vertical: 10px;
  align-items: center;
  background-color: #f4a261;
  border-radius: 6px;
`;

const SearchTabScreen = ({navigation}: SearchTabStackNavigationProps<'SearchTabScreen'>) => {
  const temp =
    '안녕하세요 반원형입니다. 오늘 하루 즐거우셨네요. 동대문과산 백두산이 마르고 닳도록 하나님이 보우하사 우리나라 만세';
  const [input, setInput] = useState<string>('');

  useEffect(() => {
    // setInput(temp);
  }, [temp]);

  return (
    <View>
      <Text>검색</Text>
      <Button onPress={() => navigation.navigate('SearchDetailScreen')}>
        <Text>도서 상세 정보</Text>
      </Button>

      <TextInput
        multiline={true}
        style={{
          fontSize: 32,
          borderWidth: 1,
          borderColor: 'red',
          color: 'black',
          flexShrink: 1,
        }}
        onChangeText={text => setInput(text)}>
        {input}
      </TextInput>
    </View>
  );
};

export default SearchTabScreen;
