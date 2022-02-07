import * as React from 'react';
import {useEffect, useState} from 'react';
import {ActivityIndicator, FlatList, Image, StatusBar, StyleSheet, TouchableOpacity, View} from 'react-native';
import {useNavigation} from '@react-navigation/native';
import {RootStackNavigationType} from '../../../navigation/RootStackNavigator';
import {COLORS} from '../../../styles/colors';
import {spacing} from '../../../styles/spacing';
import {Comment} from '../../../components/FixedText';
import {BookInfo, BookInfoContent} from './shared/SearchControl';
import {TempBookInfoProps} from '../../../types/typed';

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

const SAMPLE_BOOK_INFO: TempBookInfoProps[] = [
  {
    title: '네가 번개를 맞으면 나는 개미가 될거야 네가 번개를 맞으면 나는 개미가 될거야',
    author: '장하은',
    imageUrl: 'https://image.yes24.com/goods/106527675/XL',
    url: 'https://www.yes24.com/Product/Goods/106527675',
    publishing: '2022-02-14',
    publisher: '행복우물',
  },
  {
    title: '불편한 편의점',
    author: '김호연',
    imageUrl: 'https://image.yes24.com/goods/99308021/XL',
    url: 'https://www.yes24.com/Product/Goods/99308021',
    publishing: '2021-04-20',
    publisher: '나무옆의자',
  },
  {
    title: '오늘 밤, 세계에서 이 사랑이 사라진다 해도',
    author: '이치조 미사키, 권영주',
    imageUrl: 'https://image.yes24.com/goods/102360203/XL',
    url: 'https://www.yes24.com/Product/Goods/102360203',
    publishing: '2021-06-28',
    publisher: '모모',
  },
  {
    title: '지구 끝에 온실',
    author: '김초엽',
    imageUrl: 'https://image.yes24.com/goods/103026125/XL',
    url: 'https://www.yes24.com/Product/Goods/103026125',
    publishing: '2021-08-16',
    publisher: '자이언트북스',
  },
];

const SearchDetailScreen = () => {
  const rootNav = useNavigation<RootStackNavigationType>();
  const [resultItem, setResultItem] = useState<TempBookInfoProps[]>();

  const onClickDetail = (item: TempBookInfoProps) => {
    // todo : api check
    rootNav.navigate('BookDetailScreen', {bookDetail: item});
  };

  const BookItem = (props: TempBookInfoProps) => {
    const {imageUrl, title, author} = props;
    return (
      <TouchableOpacity style={{width: '45%'}} onPress={() => onClickDetail(props)}>
        <View>
          <Image
            source={{uri: imageUrl}}
            style={{borderRadius: 2, width: undefined, height: 240, resizeMode: 'contain'}}
          />
          <View style={{paddingHorizontal: spacing.xs, paddingTop: spacing.xs}}>
            <BookInfo color={COLORS.primaryWhite} numberOfLines={2}>
              {title}
            </BookInfo>
            <BookInfoContent color={COLORS.grey1}>{author}</BookInfoContent>
          </View>
        </View>
      </TouchableOpacity>
    );
  };

  const SeparateItem = () => <View style={{height: 30}} />;

  useEffect(() => {
    setTimeout(() => setResultItem(SAMPLE_BOOK_INFO), 1000);
  }, []);

  return (
    <Container>
      <FlatList
        data={resultItem}
        keyExtractor={(_, index) => `book_${index}`}
        ListHeaderComponent={() => <Comment color={COLORS.grey2}>{'도서 검색결과 5'}</Comment>}
        ListHeaderComponentStyle={{marginBottom: spacing.l}}
        numColumns={2}
        columnWrapperStyle={{justifyContent: 'space-between'}}
        ItemSeparatorComponent={SeparateItem}
        ListEmptyComponent={() => <ActivityIndicator color={COLORS.primaryWhite} size={'small'} />}
        renderItem={({item}) => <BookItem {...item} />}
      />
    </Container>
  );
};

export default SearchDetailScreen;
