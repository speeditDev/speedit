import React from 'react';
import {View, Text, TouchableOpacity, Modal, Alert, StyleSheet} from 'react-native';
import {Picker} from '@react-native-picker/picker';
import {Button} from '../../components/Button';
import {COLORS} from '../../styles/colors';

const YearPicker = ({setModalOpen, modalOpen, value, setValue, items}) => {
  const pickerData = data => {
    return data?.length > 0 && data.map((val, index) => <Picker.Item label={val} value={val} key={index} />);
  };

  return (
    <Modal
      animationType="slide"
      transparent={true}
      visible={modalOpen}
      onRequestClose={() => {
        Alert.alert('Modal has been closed');
      }}>
      <View style={styles.container}>
        <View style={styles.pickerContainer}>
          <Button selected={true} buttonTitle={'선택 완료'} onPress={() => setModalOpen(!modalOpen)} />
          <Picker
            selectedValue={value}
            style={{height: 50, width: '100%'}}
            onValueChange={(itemValue, itemIndex) => setValue(itemValue)}>
            {pickerData(items)}
          </Picker>
        </View>
      </View>
    </Modal>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  pickerContainer: {
    backgroundColor: COLORS.primaryWhite,
    width: '100%',
    height: '40%',
    position: 'absolute',
    bottom: 0,
  },
});

export default YearPicker;
