const Discord = require('discord.js');
const client = new Discord.Client();

const config = require('./config.json');
const command = require('./command');
const firstMessage = require('./first-message');
const roleClaim = require('./rules-role-claim');

client.on('ready', () => {
    console.log('Der Client ist bereit!');
    roleClaim(client);

    command(client, ['ping', 'test'], message => {
        message.channel.send('Pong!')

    });

    command(client, 'servers', (message) => {
        client.guilds.cache.forEach((guild) => {
            console.log(guild)

        })
    });

    command(client, ['clear', 'cc', 'clearchat'], message => {
        if(message.member.hasPermission('ADMINISTRATOR')) {
            message.channel.messages.fetch().then((messages) => {
                message.channel.bulkDelete(messages)

            })
        }
    });

    command(client, 'setstatus', message => {
        const content = message.content.replace('wtf setstatus ', '');
        client.user.setPresence( {
            activity: {
                name: content,
                type: 0

            }
        })
    });
    command(client, 'sendrules', (message) => {
        message.channel.send(embed)
    })
});

client.login(config.token);
