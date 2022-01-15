import * as React from 'react';
import styled from 'styled-components/native';
import {SearchTabStackNavigationProps} from '../../../navigation/SearchTabStackNavigator';

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
  return (
    <View>
      <Text>검색</Text>
      <Button onPress={() => navigation.navigate('SearchDetailScreen')}>
        <Text>도서 상세 정보</Text>
      </Button>
    </View>
  );
};

export default SearchTabScreen;
