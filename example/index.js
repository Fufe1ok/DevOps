const admin = require('firebase-admin');
admin.initializeApp();
var db = admin.firestore();
admin.firestore().settings({timestampsInSnapshots: true})

/**
 * Background Cloud Function to be triggered by Pub/Sub.
 * This function gets executed when telemetry data gets
 * send to IoT Core and consequently a Pub/Sub message
 * gets published to the selected topic.
 *
 * @param {Object} event The Cloud Functions event.
 * @param {Function} callback The callback function.
 */
exports.telemetryToFirestore = (event, context) => {
  const pubsubMessage = event.data;

  if (!pubsubMessage) {
    throw new Error('No telemetry data was provided!');
  }
  const payload = Buffer.from(pubsubMessage, 'base64').toString();
  console.log(Buffer.from(pubsubMessage, 'base64').toString());
  const telemetry = JSON.parse(payload);
  
  const attributes = telemetry.attributes;
  
  const deviceId = attributes.id;
  const measure = telemetry.temperature ? {
    'timestamp': attributes.time,
    'temperature': telemetry.temperature,
    'location': attributes.location
  } : (telemetry.pressure ? {
    'timestamp': attributes.time,
    'pressure': telemetry.pressure,
    'location': attributes.location
  } : {
    'timestamp': attributes.time,
    'humidity': telemetry.humidity,
    'location': attributes.location
  })

  db.collection(`devices/${deviceId}/measurements`).add(measure).then((writeResult) => {
    console.log({'result': 'Message with ID: ' + writeResult.id + ' added.'});
    return;
  }).catch((err) => {
    console.log(err);
    return;
  });
};