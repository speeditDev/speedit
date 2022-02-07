import * as React from 'react';
import {Image, ImageSourcePropType} from 'react-native';

type IconName = 'heart' | 'bookmark' | '3dot' | 'search' | 'erase' | 'share';

type IconType = {
  name: IconName;
  width: number;
  height: number;
  source: ImageSourcePropType;
};

const IconSourceList: IconType[] = [
  {name: 'heart', source: require('../images/icon_heart.png'), width: 32, height: 32},
  {name: 'bookmark', source: require('../images/icon_bookmark.png'), width: 32, height: 32},
  {name: '3dot', source: require('../images/icon_3dot.png'), width: 32, height: 32},
  {name: 'search', source: require('../images/icon_search_grey3.png'), width: 24, height: 24},
  {name: 'erase', source: require('../images/icon_close_grey2.png'), width: 16, height: 16},
  {name: 'share', source: require('../images/icon_share.png'), width: 24, height: 24},
];

// 존재하는 이름만 입력가능하도록 설정하기 떄문에, undefined 를 강제로 허용안함
const Icons = ({iconName}: {iconName: IconName}) => {
  const {source, width, height} = IconSourceList.find(item => item.name === iconName)!;
  return <Image source={source} style={{width, height}} />;
};

export default Icons;
