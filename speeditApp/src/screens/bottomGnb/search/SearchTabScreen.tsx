import * as React from 'react';
import {useEffect, useState} from 'react';
import {SearchTabStackNavigationProps} from '../../../navigation/SearchTabStackNavigator';
import {
  Alert,
  Keyboard,
  StatusBar,
  StyleSheet,
  TextInput,
  TouchableOpacity,
  TouchableWithoutFeedback,
  View,
} from 'react-native';
import {COLORS} from '../../../styles/colors';
import {spacing} from '../../../styles/spacing';
import {Button} from '../../../components/Button';
import {pretendard} from '../../../styles/textStyled';
import Icons from '../../../components/Icons';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: COLORS.black,
    paddingVertical: spacing.l,
    paddingLeft: spacing.m,
    paddingRight: spacing.l,
  },
});

const Container: React.FC = ({children}) => (
  <View style={styles.container}>
    <StatusBar barStyle={'light-content'} backgroundColor={COLORS.black} />
    {children}
  </View>
);

const BOOK_CATEGORY: string[] = [
  '고전/문학',
  '심리',
  '철학',
  '경영',
  '경제',
  '트렌드/미래예측',
  '마케팅전략',
  '재테크/투자',
  '성공/처세',
  '시간관리',
  '자기능력계발',
  '인간관계',
  '대화/협상',
  '시/에세이',
  '역사/문화',
];

const SearchTabScreen = ({navigation: {navigate}}: SearchTabStackNavigationProps<'SearchTabScreen'>) => {
  const [inputSearch, setInputSearch] = useState<string>('');

  const handleSubmit = () => {
    if (inputSearch.length < 1) {
      Alert.alert('검색어를 입력해주세요');
      return;
    }

    navigate('SearchDetailScreen', {search: inputSearch});
  };

  useEffect(() => {}, []);

  const handleErase = () => setInputSearch('');

  return (
    <TouchableWithoutFeedback onPress={Keyboard.dismiss}>
      <Container>
        <>
          <View
            style={{
              flexDirection: 'row',
              alignItems: 'center',
              paddingBottom: spacing.s,
              paddingRight: spacing.xs,
              marginBottom: spacing.blank_s,
              borderBottomWidth: 1.5,
              borderColor: COLORS.primaryWhite,
            }}>
            <Icons iconName={'search'} />
            <View style={{flex: 1, paddingHorizontal: spacing.xs}}>
              <TextInput
                multiline={false}
                numberOfLines={1}
                maxLength={30}
                placeholder={'책 제목, 또는 작가명으로 검색'}
                placeholderTextColor={COLORS.grey4}
                style={[pretendard.bold18, {color: COLORS.primaryWhite, textAlignVertical: 'bottom'}]}
                returnKeyType={'search'}
                onChangeText={text => setInputSearch(text)}
                value={inputSearch}
                onSubmitEditing={handleSubmit}
              />
            </View>
            {inputSearch.length > 0 ? (
              <TouchableOpacity
                style={{width: 24, height: 24, alignItems: 'center', justifyContent: 'center'}}
                onPress={handleErase}>
                <Icons iconName={'erase'} />
              </TouchableOpacity>
            ) : null}
          </View>

          <View style={{flex: 1, flexDirection: 'row', flexWrap: 'wrap'}}>
            {BOOK_CATEGORY.map((item, index) => (
              <View key={`category_${index}`} style={{marginRight: spacing.xs, marginBottom: spacing.s}}>
                <Button.Medium
                  selected={false}
                  buttonTitle={item}
                  onPress={() => navigate('SearchDetailScreen', {search: item})}
                />
              </View>
            ))}
          </View>
        </>
      </Container>
    </TouchableWithoutFeedback>
  );
};

export default SearchTabScreen;
