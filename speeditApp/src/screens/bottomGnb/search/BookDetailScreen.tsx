import React, {useCallback, useState} from 'react';
import {
  Dimensions,
  Image,
  ImageBackground,
  Linking,
  Platform,
  ScrollView,
  Share,
  StatusBar,
  StyleSheet,
  TouchableOpacity,
  View,
} from 'react-native';
import {RootStackNavigationProps} from '../../../navigation/RootStackNavigator';
import {HeaderBack} from '../../../components/HeaderControl';
import {Category} from '../../../components/FixedText';
import Icons from '../../../components/Icons';
import {COLORS} from '../../../styles/colors';
import {spacing} from '../../../styles/spacing';
import LinearGradient from 'react-native-linear-gradient';
import {BookContent, BookInfo, BookInfoContent, BookTitle} from './shared/SearchControl';
import {Button} from '../../../components/Button';
import {getBottomSpace} from 'react-native-iphone-x-helper';
import Animated, {useAnimatedStyle, useSharedValue, withTiming} from 'react-native-reanimated';

const {width: screenWidth, height: screenHeight} = Dimensions.get('screen');

const BLUR_SIZE = {width: screenWidth, height: screenHeight * 0.4};
const THUMBNAIL_SIZE = {width: screenWidth * 0.5, height: screenHeight * 0.3};

const SAMPLE_COMMENT =
  '제품 생산자든 마케팅 담당자든 무엇인가를 생각하고, 만들고, 내놓는 사람들은 모두 기획자다. 하지만 본인의 역량을 스스로 키우는 것 외에 기획 업무를 잘하기 위한 방법은 막연하기만 하다. 이 책의 저자는 네이버에서 브랜드 기획자로 일한다. IT 최첨단에서 빠르게 변화하는 디지털 도구들을 활용하기에도 벅찰 것 같은 저자가 ‘사람들의 생각을 읽어내기 위해’ 절실한 마음으로 가장 가까이에서 기댔던 건 예상외로 ‘책’이었다, 책을 통해 기획하는 힘을 키워나가는 동시에 책에 기대 읽고, 생각하며, 삶을 펼쳐나가는 동시대 기획자의 이야기를 만나보자. 제품 생산자든 마케팅 담당자든 무엇인가를 생각하고';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    // backgroundColor: COLORS.black,
    paddingVertical: spacing.l,
    paddingLeft: spacing.m,
    paddingRight: spacing.l,
    paddingBottom: spacing.s + getBottomSpace(),
  },
});

const Container: React.FC = ({children}) => (
  <View style={styles.container}>
    <StatusBar barStyle={'light-content'} backgroundColor={COLORS.black} />
    {children}
  </View>
);

const BookDetailScreen = ({route: {params}, navigation}: RootStackNavigationProps<'BookDetailScreen'>) => {
  const {
    bookDetail: {title, imageUrl, url, publishing, publisher, author},
  } = params;

  const [isExpand, setExpand] = useState<boolean>(false);
  const commentHeight = useSharedValue<number>(55);

  const heightAnimation = useAnimatedStyle(() => {
    return {height: withTiming(commentHeight.value, {duration: 200})};
  });

  const onExpandControl = () => {
    commentHeight.value = isExpand ? 55 : 110;
    setExpand(prev => !prev);
  };

  const onShare = async () => {
    const isAndroid = Platform.OS === 'android';
    try {
      if (isAndroid) {
        await Share.share({title: title, message: url});
      } else {
        await Share.share({title: title, url: url});
      }
    } catch (e) {
      console.log(e);
    }
  };

  const NavigationHeader = () => (
    <View
      style={{
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        paddingVertical: spacing.m,
        paddingHorizontal: spacing.m,
        backgroundColor: 'transparent',
        height: 60,
      }}>
      <HeaderBack navigate={navigation.goBack} />
      <Category>{'페이지 타이틀'}</Category>
      <TouchableOpacity
        style={{width: 30, height: 30, alignItems: 'center', justifyContent: 'center'}}
        onPress={() => onShare()}>
        <Icons iconName={'share'} />
      </TouchableOpacity>
    </View>
  );

  const BuyButton = () => {
    const handlePress = useCallback(async () => {
      await Linking.openURL(url);
      // const supported = await Linking.canOpenURL(url);
      // todo : test ( 안드로이드에서 추가 설정 필요한듯 )
      // if (supported) {
      //   await Linking.openURL(url);
      // } else {
      //   Alert.alert('유효하지 않는 주소입니다.', url);
      // }
    }, [imageUrl]);
    return <Button onPress={handlePress} buttonTitle={'책 구매하기'} />;
  };

  return (
    <View style={{flex: 1}}>
      <LinearGradient
        colors={['rgba(28,28,30,0.9)', 'rgba(28,28,30,0.95)', 'rgba(28,28,30,1)']}
        style={{flex: 1, zIndex: 10}}
        locations={[0, 0.5, 1]}>
        <ImageBackground
          blurRadius={8}
          source={{uri: imageUrl}}
          style={{
            width: BLUR_SIZE.width,
            height: '80%',
            aspectRatio: 1,
            opacity: 0.12,
            backgroundColor: COLORS.black,
          }}
          resizeMode={'stretch'}
        />
        <View style={[StyleSheet.absoluteFill]}>
          <NavigationHeader />
          <Container>
            <Image
              source={{uri: imageUrl}}
              width={THUMBNAIL_SIZE.width}
              height={THUMBNAIL_SIZE.height}
              style={{
                width: THUMBNAIL_SIZE.width,
                height: THUMBNAIL_SIZE.height,
                aspectRatio: 1,
                alignSelf: 'center',
              }}
              resizeMode={'contain'}
            />
            <View style={{flexDirection: 'row', justifyContent: 'center', paddingVertical: spacing.m}}>
              <View style={{alignItems: 'center'}}>
                <BookTitle color={COLORS.primaryWhite}>{title}</BookTitle>
                <BookContent color={COLORS.grey2}>{author}</BookContent>
              </View>
            </View>

            <View style={{flexDirection: 'row', paddingBottom: spacing.xxl}}>
              <View style={{flex: 1, alignItems: 'center'}}>
                <BookInfoContent color={COLORS.whiteSentence}>{'카테고리'}</BookInfoContent>
                <BookInfo color={COLORS.primaryWhite}>{'??'}</BookInfo>
              </View>
              <View
                style={{
                  flex: 1,
                  paddingHorizontal: spacing.m,
                  alignItems: 'center',
                  borderLeftWidth: 1.5,
                  borderRightWidth: 1.5,
                  borderColor: COLORS.grey4,
                }}>
                <BookInfoContent color={COLORS.whiteSentence}>{'출간'}</BookInfoContent>
                <BookInfo color={COLORS.primaryWhite}>{publishing}</BookInfo>
              </View>
              <View style={{flex: 1, alignItems: 'center'}}>
                <BookInfoContent color={COLORS.whiteSentence}>{'출판'}</BookInfoContent>
                <BookInfo color={COLORS.primaryWhite}>{publisher}</BookInfo>
              </View>
            </View>
            <TouchableOpacity onPress={() => onExpandControl()}>
              <Animated.View style={[heightAnimation]}>
                <BookInfo numberOfLines={isExpand ? 4 : 2} color={COLORS.whiteSentence} style={{flexWrap: 'wrap'}}>
                  {SAMPLE_COMMENT}
                </BookInfo>
              </Animated.View>
            </TouchableOpacity>

            {/*코멘트 추가할 부분.. todo : FlatList*/}
            <ScrollView></ScrollView>
            <BuyButton />
          </Container>
        </View>
      </LinearGradient>
    </View>
  );
};

export default BookDetailScreen;

// 커스텀 네비게이션을 하기 위함
// useLayoutEffect(() => {
//   navigation.setOptions({
//     headerShown: true,
//     headerStyle: {backgroundColor: 'rgba(28,28,30, 1)'},
//     headerLeft: () => <HeaderBack navigate={navigation.goBack} />,
//     headerTitle: () => <Category>{'페이지 타이틀'}</Category>,
//     headerRight: () => (
//       <TouchableOpacity
//         style={{width: 30, height: 30, alignItems: 'center', justifyContent: 'center'}}
//         onPress={() => Share.share({url: '', message: 'ㅁㅁ', title: ''})}>
//         <Icons iconName={'share'} />
//       </TouchableOpacity>
//     ),
//   });
// }, [navigation]);
