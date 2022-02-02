import * as React from 'react';
import {useRef, useState} from 'react';
import {
  Animated,
  Dimensions,
  FlatList,
  Platform,
  SafeAreaView,
  StatusBar,
  StyleSheet,
  TouchableOpacity,
  View,
} from 'react-native';
import {COLORS} from '../../../styles/colors';
import {spacing} from '../../../styles/spacing';
import {Category, Comment, Like, MyCompany, MyName, Sentence} from '../../../components/FixedText';
import Icons from '../../../components/Icons';

const {width: screenWidth} = Dimensions.get('screen');
const {height: windowHeight} = Dimensions.get('window'); // android 에서는 최상단 status 크기를 제외한 값이다. ( 높이 )

const COMMENT_MIN_HEIGHT = 26;
const COMMENT_MAX_HEIGHT = windowHeight * 0.5;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: COLORS.black,
    paddingVertical: spacing.l,
    paddingLeft: spacing.m,
    paddingRight: spacing.l,
  },
});

const FEED_WIDTH = screenWidth - spacing.m - spacing.l;

const TOP_MENU: string[] = ['전체', '팔로우', '디자이너'];

const Container: React.FC = ({children}) => (
  <View style={styles.container}>
    <SafeAreaView style={{flex: 1}}>
      <StatusBar barStyle={'light-content'} backgroundColor={COLORS.black} />
      {children}
    </SafeAreaView>
  </View>
);

interface TempFeedProps {
  content: string;
  author?: string;
  bookName?: string;
}

// 207
const TEMP_CONTENT_1 = `즉, 실행 방안을 자신의 스타일대로 설명하는 것이 아니라, 상대방의 머릿속에 기획자의 제안이 그림 그려지도록 해야 한다.\n\n즉, 
실행 방안을 자신의 스타일대로 설명하는 것이 아니라, 상대방의 머릿속에 기획자의 제안이 그림 그려지도록 해야 한다.\n\n
즉, 실행 방안을 자신의 스타일대로 설명하는 것이 아니라, 상대방의 머릿속에 기획자의 제안이 그림 그려지도록 해야 한다.`;

// 128
const TEMP_CONTENT_2 = `죽는 날까지 하늘을 우러러
한 점 부끄럼이 없기를,
잎새에 이는 바람에도
나는 괴로워했다.

별을 노래하는 마음으로
모든 죽어 가는 것을 사랑해야지
그리고 나한테 주어진 길을
걸어가야겠다.

오늘 밤에도 별이 바람에 스치운다.

`;

// 36
const TEMP_CONTENT_3 = `주말이 짧게 느껴지는 이유\n평일:월화수목금\n주말:토일\n실제로 짧음`;

const TEMP_COMMENT = `유저 코멘트가 노출, 영역 넘어가버림, 유저 코멘트가 노출, 영역 넘어가버림, 유저 코멘트가 노출, 영역 넘어가버림, 유저 코멘트가 노출, 영역 넘어가버림`;

const TEMP_FEEDS: TempFeedProps[] = [...new Array(10)].map((_, index) => {
  const rest = index % 3;
  return {
    content: rest === 0 ? TEMP_CONTENT_1 : rest === 1 ? TEMP_CONTENT_2 : TEMP_CONTENT_3,
    bookName: `테스트 책이름(${index})`,
    author: `홍길동(${index})`,
  };
});

const HomeTabScreen = () => {
  const [menuIndex, setMenuIndex] = useState<number>(0);
  const [isExtend, setExtend] = useState<boolean>(false);
  const commentHeight = useRef(new Animated.Value(COMMENT_MIN_HEIGHT)).current;

  const onExtendCommentHeight = () => {
    Animated.timing(commentHeight, {
      toValue: COMMENT_MAX_HEIGHT,
      useNativeDriver: false,
      duration: 200,
    }).start(() => setExtend(true));
  };

  const onFoldCommentHeight = () => {
    Animated.timing(commentHeight, {
      toValue: COMMENT_MIN_HEIGHT,
      useNativeDriver: false,
      duration: 200,
    }).start(() => setExtend(false));
  };

  const feedOpacity = commentHeight.interpolate({
    inputRange: [COMMENT_MIN_HEIGHT, COMMENT_MAX_HEIGHT],
    outputRange: [1, 0.2],
    extrapolate: 'clamp',
  });

  const FeedItem = ({content, bookName, author}: TempFeedProps) => {
    const size = content.length >= 150 ? 'L' : content.length >= 50 ? 'M' : 'S';
    return (
      <View style={{width: FEED_WIDTH}}>
        <Animated.View style={{flex: 1, opacity: feedOpacity, backgroundColor: COLORS.black}}>
          <Sentence lengthSize={size}>{content}</Sentence>
          <Comment>{`기획자의 독서, 김도영 저`}</Comment>
        </Animated.View>
        {/*하단*/}
        <View style={{flex: 0.6, flexDirection: 'row'}}>
          {/*좌측 커멘트*/}
          <View style={{flex: 1, justifyContent: 'flex-end'}}>
            <View style={{flexDirection: 'row', paddingBottom: spacing.m}}>
              <View
                style={{width: 40, height: 40, borderRadius: 20, backgroundColor: COLORS.grey1, marginRight: spacing.s}}
              />
              <View>
                <MyName>{author}</MyName>
                <MyCompany>{`회사명 | 직업명`}</MyCompany>
              </View>
            </View>
            <Animated.View
              style={{
                height: commentHeight,
                flexDirection: 'row',
                backgroundColor: COLORS.black,
                alignItems: 'flex-start',
                zIndex: 10,
              }}>
              <View style={{flex: 1}}>
                <Comment style={{textAlignVertical: 'top'}}>{TEMP_COMMENT}</Comment>
              </View>
              <TouchableOpacity
                onPress={() => (isExtend ? onFoldCommentHeight() : onExtendCommentHeight())}
                style={{width: 20, height: 20, backgroundColor: 'red'}}
              />
            </Animated.View>
          </View>
          {/*우측 메뉴*/}
          <View style={{width: 32, justifyContent: 'space-between', alignItems: 'center'}}>
            <TouchableOpacity>
              <Icons iconName={'3dot'} />
            </TouchableOpacity>
            <TouchableOpacity>
              <View>
                <Icons iconName={'heart'} />
                <Like>{`999+`}</Like>
              </View>
            </TouchableOpacity>
            <TouchableOpacity>
              <Icons iconName={'bookmark'} />
            </TouchableOpacity>
            <TouchableOpacity>
              <View style={{width: 26, height: 35, backgroundColor: COLORS.grey1}} />
            </TouchableOpacity>
          </View>
        </View>
      </View>
    );
  };

  return (
    <Container>
      <>
        <View style={{flexDirection: 'row', height: 48, alignItems: 'center'}}>
          {TOP_MENU.map((item, index) => (
            <TouchableOpacity
              key={`menu_${index}`}
              onPress={() => setMenuIndex(index)}
              style={{marginRight: spacing.m}}>
              <Category color={menuIndex === index ? COLORS.primaryWhite : COLORS.grey3}>{item}</Category>
            </TouchableOpacity>
          ))}
        </View>

        <FlatList
          horizontal={true}
          // style={{flex: 1}}
          // contentContainerStyle={{flex: 1}}
          onMomentumScrollBegin={() => onFoldCommentHeight()}
          scrollEventThrottle={16}
          showsHorizontalScrollIndicator={false}
          snapToAlignment={'center'}
          pagingEnabled={true}
          decelerationRate={Platform.OS === 'android' ? 0.9 : 0}
          snapToInterval={FEED_WIDTH}
          scrollToOverflowEnabled={false}
          data={TEMP_FEEDS}
          renderItem={({item}) => <FeedItem {...item} />}
        />
      </>
    </Container>
  );
};

export default HomeTabScreen;
