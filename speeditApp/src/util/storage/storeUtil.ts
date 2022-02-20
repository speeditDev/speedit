import AsyncStorage from '@react-native-async-storage/async-storage';

type ASYNCSTORAGE_KEYS = '@ACCESS_TOKEN' | '@REFRESH_TOKEN';

export const storeSetData = async (key: ASYNCSTORAGE_KEYS, value: string) => {
  // const isValueObject = typeof value === 'object';
  try {
    await AsyncStorage.setItem(key, value);
  } catch (e) {
    // saving error
  }
};

export const storeGetData = async (key: ASYNCSTORAGE_KEYS): Promise<string | undefined> => {
  try {
    const value = await AsyncStorage.getItem(key);
    if (value !== null) {
      // value previously stored
      return value;
    } else {
      return '';
    }
  } catch (e) {
    // error reading value
  }
};
