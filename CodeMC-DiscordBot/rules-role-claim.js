const Discord = require('discord.js')

const firstMessage = require('./first-message')

module.exports = (client) => {
  const channelId = '812442608337027162'

  const getEmoji = (emojiName) =>
    client.emojis.cache.find((emoji) => emoji.name === emojiName)

  const emojis = {
    tick_gif: '〔✔〕Verifiziert',

  }

  const reactions = []

  let emojiText = new Discord.MessageEmbed().setTitle('📗» Regeln').setAuthor('CodeMC', 'https://i.ibb.co/SBT154Q/codemc-transparent.png', 'https://codemc.eu').setColor('#00FF00').setDescription('§1 Namensgebung\nUm der Übersichtlichkeit einen Beitrag zu leisten würden wir die Spieler bzw. User bitten, den gleichen Namen Ingame sowie auf dem Discord und dem TeamSpeak zu verwenden.\n \n§2 Störungen\nBei Team Besprechungen bzw. normalen Gesprächen würden wir die User und Teammitglieder bitten, keine Soundboards zu verwenden, das stört alle User, und es wird auch bei ständigem ermahnen gebannt!\n \n§3 Beleidigung und Streitigkeiten\nWir dulden keine Beleidigungen, denn wir wollen eine freundliche Atmosphäre auf unserem Server schaffen, diejenigen die das trotzdem machen, werden gekickt bzw. gebannt! Streit sollte immer privat und nicht auf dem Server ausgeführt werden. Freundschaftliche Beleidigungen sind okay.\n \n§4 Channel Hopping\nChannel Hopping ist verboten. Das bedeutet, nicht einfach durch die Gegend herumzuswitchen. Wer widerstößt, fliegt.\n \n§5 Datenschutz\nPrivate Daten, wie Telefonnummern, Adressen, Passwörter, usw. dürfen nicht öffentlich ausgetauscht werden. Ein Server-Admin oder vom Team eingesetzter Channel-Admin wird niemals nach deinem Passwort o.ä. fragen. Wenn dies doch der Fall ist melde dies den Ownern bzw dem Manager.\n \n§6 Inhalte\nDas Benutzen von Wörtern, welche den Anschein erwecken könnten, dass du ein Mitglied des Teams bist oder welche menschenfeindlich, rassistisch, sexistisch, pornographisch, nationalsozialistisch, urheberrechtlich geschützt oder diskriminierend sind, sowie Avatare die das Persönlichkeitsrecht einer Person verletzten, sind verboten. Wir sind dazu berechtigt Nachrichten aus den Textbereichen zu löschen. Die Nachrichten müssen zu dem Thema des Textchannels passen!\n \n§7 Support\nWir bitten die Spieler, den Support nicht auszunutzen, da das sehr viel Stress für die Teammitglieder bedeutet. Tickets werden nach 1 Stunde Inaktivität geschlossen. Falls erneut Fragen auftreten, einfach wieder ein Ticket öffnen.\n \nSchlusswort\nDas Team behält sich vor die Regeln jederzeit und ohne Ankündigung zu ändern.')
  for(const key in emojis) {
    const emoji = getEmoji(key)
    reactions.push(emoji)
    const role = emojis[key]

    }

  firstMessage(client, channelId, emojiText, reactions)

  const handleReaction = (reaction, user, add) => {
    if(user.id === '822598822735380531') {
      return

    }

    const emoji = reaction._emoji.name

    const { guild } = reaction.message

    const roleName = emojis[emoji]
    if(!roleName) {
      return

    }

    const role = guild.roles.cache.find(role => role.name === roleName)
    const member = guild.members.cache.find(member => member.id === user.id)

    if(add) {
      member.roles.add(role)

    }else
    member.roles.remove(role)
    
  }
  
  client.on('messageReactionAdd', (reaction, user) => {
    if(reaction.message.channel.id === channelId) {
      handleReaction(reaction, user, true)

    }
  })

  client.on('messageReactionRemove', (reaction, user) => {
    if(reaction.message.channel.id === channelId) {
      handleReaction(reaction, user, false)

    }
  })
}